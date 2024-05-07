package com.rose.yaj.listener;/**
 * @author rose
 * @create 2024/5/7
 */

import com.rose.common.feignDto.RegisterFeign;
import com.rose.yaj.service.WeChatService;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <br>
 */
@Component
@RocketMQMessageListener(consumerGroup = "consumeRegister", topic = "TX_REGISTER_ADD",consumeMode = ConsumeMode.ORDERLY)
public class RegisterListener implements RocketMQListener<RegisterFeign> {

    @Autowired
    private WeChatService weChatService;


    /**
     *
     * @param dto
     */
    @Override
    public void onMessage(RegisterFeign dto) {
        System.out.println("接收到消息，开始消费..dto" + dto);
        weChatService.registByOpenid(dto);

    }

}
