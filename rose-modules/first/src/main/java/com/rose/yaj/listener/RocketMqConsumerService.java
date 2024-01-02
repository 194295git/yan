package com.rose.yaj.listener;

import com.alibaba.fastjson.JSON;
import com.rose.common.base.WebsocketMessage;
import com.rose.common.constant.NettyConstants;
import com.rose.common.mqutil.MqMessage;
import com.rose.common.mqutil.SendRequest;
import com.rose.common.utils.UUIDUtils;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.service.YanUserChatService;
import io.github.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer;
import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqTopic;
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
    YanUserChatService yanUserChatService;

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
            String mqmsg = new String(msg.getBody());
            log.info("RocketMqConsumerService=====消费消息:"+mqmsg);
            //消息内容
            MqMessage message1 = JSON.parseObject(mqmsg, MqMessage.class);

            ChatDto chatDto = new ChatDto();
            chatDto.setContent(message1.getInfoContent());
            chatDto.setToOpenid(message1.getToId());
            chatDto.setGroup(message1.getState());
            if (message1.getState() !=null){
                if(message1.getType().equals("onLine")){
                    yanUserChatService.saveChat(message1.getFromId(),chatDto,1);
                }else {
                    yanUserChatService.saveChat(message1.getFromId(),chatDto,0);
                }
            }
            //处理业务逻辑
            return true;
        }catch (Exception e){
            log.error("推送失败.",e);
        }
        return false;
    }

    //构造推送消息体
    private WebsocketMessage getMessage(String channelId, SendRequest request, MessageExt msg) {
        WebsocketMessage websocketMsg = new WebsocketMessage(
                request.getRequestId(),
                "savedb",
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
//        String instanceId =   nettyutil.getInstance();

        map.put(NettyConstants.ROCKETMQ_TPOIC_SAVECHAT, null);
        return map;
    }

    /**
     * 消费组为服务实例名
     * @return
     */
    @Override
    public String getConsumerGroup() {
        return "consumeNetty";
//        String instanceId =   nettyutil.getInstance();
//        return RocketMQConfig.getWebsocketGroup(instanceId);
    }

}