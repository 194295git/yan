package com.rose.demo.MQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rose
 * @create 2021/8/9
 */
@Configuration
public class FanoutRabbitConfig {

    public static final String DEFAULT_BOOK_QUEUE = "dev.book.fanout.a.queue";

    @Bean
    public Queue queueMessageA() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(DEFAULT_BOOK_QUEUE, true);
    }



    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessageA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessageA).to(fanoutExchange);
    }

}

