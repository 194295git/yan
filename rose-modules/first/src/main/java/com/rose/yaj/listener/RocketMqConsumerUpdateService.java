package com.rose.yaj.listener;

import com.rose.yaj.service.YanUserChatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理MQ中的推送消息，对客户端发起推送
 */
@RocketMQMessageListener(topic = "UPDATECHAT",consumerGroup ="consumeUpdateNetty")
@Component
@Slf4j
public class RocketMqConsumerUpdateService implements RocketMQListener<String>,  RocketMQPushConsumerLifecycleListener {

    @Autowired
    YanUserChatService yanUserChatService;


    /**
     * 批量的进行更新消息的状态.
     * @param consumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 设置消费者的其他属性，如果需要的话
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                List<String> msgid = new ArrayList<String>();
                // 在这里实现批量消息处理逻辑
                for (MessageExt msg : msgs) {
                    // 处理单条消息
                    msgid.add(new String(msg.getBody()));
                }
                yanUserChatService.updateChatByMsgid(msgid);
                // 返回消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }

    /**
     * 先这样子监听一下
     * @param s
     */
    @Override
    public void onMessage(String s) {

    }
}