package com.netty.common.config;

import com.rose.common.to.mq.Message2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @创建人 rose
 * @创建时间 2021/12/10
 * @描述
 */
@Slf4j
@Component
public class MQUtils {
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    public void MessageSend(String topic, Object message) {
//        rocketMQTemplate.convertAndSend(topic,message);
//        log.info("MQUtils.MessageSend rocket消息队列的消息已经发送拉...");
//    }
    public void MessageSend2(Message2 message) {
        rabbitTemplate.convertAndSend("yan-exchange","netty",message);
        log.info("rabbitmq  消息队列的消息已经发送拉...");
    }

}
