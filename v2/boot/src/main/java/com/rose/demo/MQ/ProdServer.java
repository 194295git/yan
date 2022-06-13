package com.rose.demo.MQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProdServer {

    private static final Logger log = LoggerFactory.getLogger(ProdServer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    /**
     * direct模式下发送消息
     * @param obj
     * @throws JsonProcessingException
     */
    public void convertAndSend(Object obj) throws JsonProcessingException {

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("log.user.direct.exchange"));
        rabbitTemplate.setRoutingKey(env.getProperty("log.user.direct.routing.key"));

        Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(obj))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                MessageProperties.CONTENT_TYPE_JSON);

        rabbitTemplate.convertAndSend(message);
        log.info("--------------------消息传入MQ成功-----------------");
    }

    /**
     * fanout模式下发送消息
     * @param obj
     */
    public void fanoutSendMsg(Object obj) throws JsonProcessingException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("log.user.fanout.exchange"));
        rabbitTemplate.setRoutingKey("");

        Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(obj))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                MessageProperties.CONTENT_TYPE_JSON);

        log.info("【消息发送者】发送消息到fanout交换机，消息内容为: {}", message);
        rabbitTemplate.convertAndSend(message);
        log.info("--------------------消息传入MQ成功-----------------");
    }

    /**
     * topic模式下发送消息
     * @param obj
     */
    public void topicSendMsg1(Object obj) throws JsonProcessingException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("log.user.topic.exchange"));
        rabbitTemplate.setRoutingKey(env.getProperty("log.user.topic.routing.key1"));

        Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(obj))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                MessageProperties.CONTENT_TYPE_JSON);

        log.info("【消息发送者1】发送消息到fanout交换机，消息内容为: {}", message);
        rabbitTemplate.convertAndSend(message);
    }

    /**
     * topic模式下发送消息
     * @param obj
     */
    public void topicSendMsg2(Object obj) throws JsonProcessingException {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("log.user.topic.exchange"));
        rabbitTemplate.setRoutingKey(env.getProperty("log.user.topic.routing.key2"));

        Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(obj))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                MessageProperties.CONTENT_TYPE_JSON);

        log.info("【消息发送者2】发送消息到fanout交换机，消息内容为: {}", message);
        rabbitTemplate.convertAndSend(message);
    }

    /**
     * headers模式下发送消息
     * @param obj
     */
//    public void headersSendMsg1(Object obj) throws JsonProcessingException {
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.setExchange(env.getProperty("log.user.headers.exchange"));
//
//        Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(obj))
//                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
//        message.getMessageProperties().setHeader("user","102");
//
//        log.info("【消息发送者1】发送消息到headers交换机，消息内容为: {}", message);
//        rabbitTemplate.convertAndSend(message);
//    }

    /**
     * headers模式下发送消息
     * @param obj
     */
//    public void headersSendMsg2(Object obj) throws JsonProcessingException {
//        Map<String, Object> map_all = new HashMap<>();
//        map_all.put("user", "101");
//        map_all.put("pwd", "202");
//
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.setExchange(env.getProperty("log.user.headers.exchange"));
//
//        Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(obj))
//                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
//        message.getMessageProperties().getHeaders().putAll(map_all);
//
//        log.info("【消息发送者2】发送消息到headers交换机，消息内容为: {}", message);
//        rabbitTemplate.convertAndSend(message);
//    }
}
