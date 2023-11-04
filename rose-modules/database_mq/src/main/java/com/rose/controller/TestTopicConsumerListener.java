package com.rose.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rose.dao.MQConsumerLogMapper;
import com.rose.entity.MQConsumerLog;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author rose
 * @create 2023/7/17
 */
@Slf4j
@Component
@RocketMQMessageListener(
        consumerGroup = "testConsumerGroup",
        topic = "test-topic"
)
public class TestTopicConsumerListener implements RocketMQListener<MessageExt> {

    @Autowired
    private MQConsumerLogMapper mqConsumerLogMapper;


    @SneakyThrows
    @Override
    public void onMessage(MessageExt messageExt) {
        String topic = messageExt.getTopic();
        String tags = messageExt.getTags();
        String keys = messageExt.getKeys();
        String body = new String(messageExt.getBody(), "UTF-8");
        log.info("开始消费：topic={}, tags={}, keys={}, body={}", topic, tags, keys, body);

        MQConsumerLog mqConsumerLog = null;
        try {
            // 查询消息
            mqConsumerLog = mqConsumerLogMapper.selectOne(Wrappers.<MQConsumerLog>lambdaQuery()
                    .eq(MQConsumerLog::getTopic, topic)
                    .eq(MQConsumerLog::getTags, tags)
                    .eq(MQConsumerLog::getKeys, keys)
                    .orderByDesc(MQConsumerLog::getCreateTime)
                    .last(" limit 1 ")
            );
            if (mqConsumerLog == null) {
                return;
            }

            // 判断消息状态
            Integer status = mqConsumerLog.getStatus();
            if (status == 1 || status == 3) {
                // 处理中或者处理成功都不需要重复处理(消息幂等性)
                return;
            } else if (status == 2) {
                // 2 处理失败
                if (mqConsumerLog.getConsumeTimes() >= 3) {
                    // 超过指定消费次数记录日志，人工干预
                    return;
                }
            }
            // 处理业务逻辑

            // 使用乐观锁修改消息状态为成功，如果update失败表示并发修改，打印日志即可
            MQConsumerLog entity = new MQConsumerLog();
            entity.setStatus(3);
            entity.setConsumeTimes(mqConsumerLog.getConsumeTimes() + 1);
            entity.setUpdateTime(new Date());
            entity.setVersion(mqConsumerLog.getVersion() + 1);

            int row = mqConsumerLogMapper.update(entity, Wrappers.<MQConsumerLog>lambdaUpdate()
                    .eq(MQConsumerLog::getId, mqConsumerLog.getId())
                    .eq(MQConsumerLog::getVersion, mqConsumerLog.getVersion())
            );
            if (row < 0) {
                log.info("并发修改");
            } else {
                log.info("消费成功：topic={}, tags={}, keys={}, body={}", topic, tags, keys, body);
            }
        } catch (Exception e) {
            // 标记消息状态为处理失败，并累加消费次数
            MQConsumerLog entity = new MQConsumerLog();
            entity.setId(mqConsumerLog.getId());
            entity.setStatus(2);
            entity.setConsumeTimes(mqConsumerLog.getConsumeTimes() + 1);
            entity.setException(e.getMessage());
            entity.setUpdateTime(new Date());
            entity.setVersion(mqConsumerLog.getVersion());
            mqConsumerLogMapper.updateById(entity);
        }
    }
}
