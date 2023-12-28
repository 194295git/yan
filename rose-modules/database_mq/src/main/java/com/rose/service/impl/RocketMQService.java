package com.rose.service.impl;

import com.rose.dao.MQConsumerLogMapper;
import com.rose.dao.MQProducerTempMapper;
import com.rose.entity.MQConsumerLog;
import com.rose.entity.MQProducerTemp;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author rose
 * @create 2023/7/16
 */
@Slf4j
@Component
public class RocketMQService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private MQConsumerLogMapper mqConsumerLogMapper;

    @Autowired
    private MQProducerTempMapper mqProducerTempMapper;


    public void send(Message message) throws UnsupportedEncodingException {
        // 1. 保证100%发送成功，持久化到数据库
        MQProducerTemp temp = new MQProducerTemp();
        temp.setTopic(message.getTopic());
        temp.setTags(message.getTags());
        temp.setKeys(message.getKeys());
        temp.setBody(new String(message.getBody(), "UTF-8"));
        temp.setCreateTime(new Date());

        MQConsumerLog consumeLog = new MQConsumerLog();
        BeanUtils.copyProperties(temp, consumeLog);

        // 2. 发送消息到RocketMQ
        SendResult sendResult = null;
        try {
            mqProducerTempMapper.insert(temp);
            mqConsumerLogMapper.insert(consumeLog);

            sendResult = rocketMQTemplate.getProducer().send(message);
            // 3. 发送成功删除临时消息
            if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                // 删除消息
                mqProducerTempMapper.deleteById(temp.getId());
            } else {
                // 可以尝试再次发送或者后面由定时任务定时发送
            }
        } catch (Exception e) {
            log.error("消息发送失败： {}", e);
            // 发送失败打记录错误原因
            MQProducerTemp entity = new MQProducerTemp();
            entity.setId(consumeLog.getId());
            entity.setException(e.getMessage());
            mqProducerTempMapper.updateById(entity);
        }
    }
}
