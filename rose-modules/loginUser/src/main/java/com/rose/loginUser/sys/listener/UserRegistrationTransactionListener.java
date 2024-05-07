package com.rose.loginUser.sys.listener;/**
 * @author rose
 * @create 2024/5/4
 */

import com.alibaba.fastjson.JSON;
import com.rose.loginUser.sys.dao.SysUserDao;
import com.rose.loginUser.sys.entity.SysUserEntity;
import com.rose.loginUser.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author rose<br>
 */
@Slf4j
@RocketMQTransactionListener
public class UserRegistrationTransactionListener implements RocketMQLocalTransactionListener {


    @Autowired
    private SysUserService sysUserService;


    @Autowired
    SysUserDao sysUserDao;

//    @Override
//    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
//        // 实现本地事务逻辑
//        SysUserEntity sysUserEntity = (SysUserEntity) arg;
//        try {
//            sysUserService.saveUser(sysUserEntity);
//            return LocalTransactionState.COMMIT_MESSAGE;
//        } catch (Exception e) {
//            return LocalTransactionState.ROLLBACK_MESSAGE;
//        }
//    }
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info(">>>> TX message listener execute local transaction, message={},args={} <<<<",msg,arg);
        // 执行本地事务
        RocketMQLocalTransactionState result = RocketMQLocalTransactionState.COMMIT;
        try {
            String jsonString = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
//            OrderEntity order = GSON.fromJson(jsonString, OrderEntity.class);
            SysUserEntity  sysUserEntity = JSON.parseObject(jsonString, SysUserEntity.class);
            sysUserService.saveUser(sysUserEntity);
        } catch (Exception e) {
            log.error(">>>> exception message={} <<<<",e.getMessage());
            result = RocketMQLocalTransactionState.UNKNOWN;
        }
//        return  RocketMQLocalTransactionState.UNKNOWN;
        return result;
    }


    /**
     * 步骤四
     * 描述：mq回调检查本地事务执行情况
     * @param msg
     * @return
     */

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info(">>>> TX message listener check local transaction, message={} <<<<",msg.getPayload());
        // 检查本地事务
        RocketMQLocalTransactionState result = RocketMQLocalTransactionState.COMMIT;
        try {
            String jsonString = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
            SysUserEntity  sysUserEntity = JSON.parseObject(jsonString, SysUserEntity.class);
//            OrderEntity order = GSON.fromJson(jsonString, OrderEntity.class);
//            List<OrderEntity> list = orderService.selectOrder(order);
            List<Map> list = sysUserDao.queryUserByOpenid(sysUserEntity.getOpenid(),sysUserEntity.getUsername());
            if(list.size()<=0){
                result = RocketMQLocalTransactionState.UNKNOWN;
            }

        } catch (Exception e) {
            // 异常就回滚
            log.error(">>>> exception message={} <<<<",e.getMessage());
            result = RocketMQLocalTransactionState.ROLLBACK;
        }
        return result;
    }


}