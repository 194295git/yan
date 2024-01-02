package com.netty.informationServe.message;

import com.alibaba.fastjson.JSON;
import com.rose.common.constant.NettyConstants;
import com.rose.common.netty.AttrConstants;
import com.rose.common.mqutil.SendRequest;
import com.netty.informationServe.config.RocketMQConfig;
import com.netty.informationServe.utils.Nettyutil;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.base.WebsocketMessage;
import com.rose.common.utils.UUIDUtils;
import io.github.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer;
import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTopic;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 处理MQ中的推送消息，对客户端发起推送
 */
@Service
@Slf4j
public class RocketMqConsumerService extends AbstractRocketMqConsumer<RocketMqTopic, RocketMqContent> {

    @Autowired
    private Nettyutil nettyutil;



    @Autowired
    private MessageSendService messageSendService;

    /**
     * 消费rocketmq的消息，通过channel管道来完成消息的推送
     * 使用返回值 ConsumeStatus的情况下可以确定是不是    ConsumeStatus.CONSUME_SUCCESS;
     * ConsumeStatus作为consumeMsg方法的返回值类型可以提供更好的信息反馈和异常处理，同时可读性也更好。
     * @param content
     * @param msg
     * @return
     */
    @Override
    public boolean consumeMsg(RocketMqContent content, MessageExt msg) {
        try {
            String MqMessage = new String(msg.getBody());
            log.info("RocketMqConsumerService=====消费消息:"+MqMessage);
            //消息内容
            SendRequest request = JSON.parseObject(MqMessage,SendRequest.class);
            if(request.getSendToAll()){
                //遍历该服务上的所有客户端进行推送

                for(String channelId : SessionUtils.getAllOnlineChannel().keySet()){
                    messageSendService.sendMessage(channelId,getMessage(channelId,request,msg));
                }
                return true;
            }
            //根据请求标识进行推送
            for(String channelId : request.getTo()){
                messageSendService.sendMessage(channelId,getMessage(channelId,request,msg));
            }
            return true;
        }catch (Exception e){
            log.error("推送失败.",e);
        }
        return false;
    }

    //构造推送消息体
    private WebsocketMessage getMessage(String channelId, SendRequest request, MessageExt msg) {
        Channel channel =SessionUtils.getChannel(channelId);
        WebsocketMessage websocketMsg = new WebsocketMessage(
                request.getRequestId(),
                channel.attr(AttrConstants.sessionId).get(),
                UUIDUtils.getUUID(),
                WebsocketMessage.MsgType.BUSSINESS.code,
                new String[]{channelId},
                request.getMsg(),
                request.getFrom(),
                Integer.parseInt(msg.getUserProperty(NettyConstants.Trigger))
                );
        return websocketMsg;
    }


    /**
     * 订阅该服务实例名的topic
     * @return
     */
    @Override
    public Map<String, Set<String>> subscribeTopicTags() {
        Map<String, Set<String>> map = new HashMap<>();
        String instanceId =   nettyutil.getInstance();

        map.put(RocketMQConfig.getWebsocketTopic(instanceId), null);
        return map;
    }

    /**
     * 消费组为服务实例名
     * @return
     */
    @Override
    public String getConsumerGroup() {
        String instanceId =   nettyutil.getInstance();
        return RocketMQConfig.getWebsocketGroup(instanceId);
    }

}