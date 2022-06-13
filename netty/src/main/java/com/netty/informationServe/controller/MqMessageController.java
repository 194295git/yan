package com.netty.informationServe.controller;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author rose
 * @create 2022/6/13
 */
@RestController
@RequestMapping("order/mqmessage")
public class MqMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @RequestMapping("/msg")
    public void testSendMessage(){
        for (int i = 0; i < 10 ; i++) {
            rabbitTemplate.convertAndSend("yan-exchange",
                    "netty","hello java"+i,
                    new CorrelationData(UUID.randomUUID().toString()));

        }

    }

}
