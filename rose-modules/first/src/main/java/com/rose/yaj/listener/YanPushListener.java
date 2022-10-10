package com.rose.yaj.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author rose
 * @create 2022/10/10
 */
@Slf4j
@RabbitListener(queues = "yan.push.queue")
@Component
public class YanPushListener {


}
