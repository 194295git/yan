package com.rose.yaj.listener;/**
 * @author rose
 * @create 2024/5/7
 */

import com.rose.common.feignDto.RegisterFeign;
import com.rose.yaj.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author <br>
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumeRegister", topic = "TX_REGISTER_ADD",consumeMode = ConsumeMode.ORDERLY)
public class RegisterListener implements RocketMQListener<RegisterFeign> {

    @Autowired
    private WeChatService weChatService;


    /**
     *org.springframework.dao.DuplicateKeyException:
     * ### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'test05' for key 'yan_im_user.user_name_unique'
     * ### The error may involve com.rose.yaj.mapper.YanUserMapper.insert-Inline
     * ### The error occurred while setting parameters
     * ### SQL: INSERT INTO yan_im_user  ( openid,  username,       create_time, update_time )  VALUES  ( ?,  ?,       ?, ? )
     * ### Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'test05' for key 'yan_im_user.user_name_unique'
     * ; Duplicate entry 'test05' for key 'yan_im_user.user_name_unique'; nested exception is java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'test05' for key 'yan_im_user.user_name_unique'
     * @param dto
     */
    @Override
    public void onMessage(RegisterFeign dto) {
        log.info("接收到消息，开始消费..dto" + dto);
        try {
            weChatService.registByOpenid(dto);
        }
        catch (Exception e){
            if ( e instanceof SQLIntegrityConstraintViolationException){
                log.info("已经插入成功，是重试消息，不用进行操作，不用抛出异常");
            }else if(e  instanceof DuplicateKeyException){
                log.info("已经插入成功，是重试消息，不用进行操作，不用抛出异常");

            }else{
                //这个地方可以拿一张表记录一下失败次数。如果失败的超过三次则也不抛出异常。不用重试了
                throw e;
            }
        }

    }

}
