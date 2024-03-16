package com.netty.informationServe.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author rose
 * @create 2022/3/11
 */
@Configuration
public class MyMQConfig {

    @Value("${myRabbitmq.MQConfig.queues}")
    private String queues;

    @Value("${myRabbitmq.MQConfig.exchange}")
    private String eventExchange;

    @Value("${myRabbitmq.MQConfig.routingKey}")
    private String routingKey;

    @Value("${myRabbitmq.MQConfig.queues}")
    private String pushQueues;

    @Value("${myRabbitmq.MQConfig.exchange}")
    private String pushEventExchange;

    @Value("${myRabbitmq.PushConfig.routingKey}")
    private String pushRoutingKey;
    //创建一个队列
    @Bean
    public Queue pushQueue(){
        Queue queue = new Queue(pushQueues, true, false, false);
        return queue;
    }
    //创建一个交换机
    @Bean
    public Exchange pushExchange(){
        return new TopicExchange(pushEventExchange, true, false);
    }
    //创建一个队列

    // 绑定队列 交换机 和 key
    @Bean
    public Binding pushBinding(){
        return new Binding(pushQueues, Binding.DestinationType.QUEUE, pushEventExchange, pushRoutingKey, null);
    }


    @Bean
    public Queue orderReleaseOrderQueue(){
        Queue queue = new Queue(queues, true, false, false);
        return queue;
    }

    //创建一个交换机
    @Bean
    public Exchange orderEventExchange(){
        return new TopicExchange(eventExchange, true, false);
    }
    // 绑定队列 交换机 和 key
    @Bean
    public Binding orderReleaseOrderBinding(){
        return new Binding(queues, Binding.DestinationType.QUEUE, eventExchange, routingKey, null);
    }


}
