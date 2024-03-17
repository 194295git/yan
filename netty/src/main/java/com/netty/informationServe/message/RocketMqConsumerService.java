package com.netty.informationServe.message;

import com.alibaba.fastjson.JSON;
import com.netty.informationServe.config.RocketMQConfig;
import com.netty.informationServe.utils.Nettyutil;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.mqutil.SendRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 处理MQ中的推送消息，对客户端发起推送
 *
 */
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic}",consumerGroup ="${rocketmq.consumer.group}")
@Component
@Slf4j
public class RocketMqConsumerService  implements RocketMQListener<String> {

    @Autowired
    private Nettyutil nettyutil;



    @Autowired
    private MessageSendService messageSendService;

    @Override
    public void onMessage(String o) {
//        String MqMessage = new String(msg.getBody());
        String MqMessage = o;
        log.info("RocketMqConsumerService=====消费消息:"+MqMessage);
        //消息内容
        SendRequest request = JSON.parseObject(MqMessage,SendRequest.class);
        if(request.getSendToAll()){
            //遍历该服务上的所有客户端进行推送

            for(String channelId : SessionUtils.getAllOnlineChannel().keySet()){
                messageSendService.sendMessage(channelId,nettyutil.getMessage(channelId,request));
            }
        }
        //根据请求标识进行推送
        for(String channelId : request.getTo()){
            messageSendService.sendMessage(channelId,nettyutil.getMessage(channelId,request));
        }
    }


    /**
     * 消费rocketmq的消息，通过channel管道来完成消息的推送
     * 使用返回值 ConsumeStatus的情况下可以确定是不是    ConsumeStatus.CONSUME_SUCCESS;
     * ConsumeStatus作为consumeMsg方法的返回值类型可以提供更好的信息反馈和异常处理，同时可读性也更好。
     * @param msg
     * @return
     */




    /**
     * 订阅该服务实例名的topic
     * @return
     */
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
    public String getConsumerGroup() {
        String instanceId =   nettyutil.getInstance();
        return RocketMQConfig.getWebsocketGroup(instanceId);
    }

}