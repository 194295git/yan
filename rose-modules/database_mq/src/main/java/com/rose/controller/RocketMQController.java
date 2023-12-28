package com.rose.controller;

import com.rose.service.impl.RocketMQService;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author rose
 * @create 2023/7/17
 */
@RestController
@RequestMapping("/mq")
public class RocketMQController {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private RocketMQService rocketMQService;

    /**
     * 这是错误的吧，要达到消息的最终一致性，
     * 保存消息必须和你的业务是同一个事务并且一起保存，
     * 然后异步线程发送mq。不能在多线程里面保存本地消息表，否则这样并不能保证一致性。
     *
     *
     * 可能是需要使用到异步编排的，这块异步线程池和编排这块也设计好多知识点
     */
    @RequestMapping("/send")
    public void send() {
        // 发送消息
        Message message = new Message("test-topic", "test-tag", "1", "msg boday".getBytes());
        threadPoolTaskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    rocketMQService.send(message);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
