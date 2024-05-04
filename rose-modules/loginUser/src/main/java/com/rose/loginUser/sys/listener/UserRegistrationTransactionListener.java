package com.rose.loginUser.sys.listener;/**
 * @author rose
 * @create 2024/5/4
 */

import com.rose.loginUser.sys.entity.SysUserEntity;
import com.rose.loginUser.sys.service.SysUserService;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rose<br>
 */
public class UserRegistrationTransactionListener implements TransactionListener {


    @Autowired
    private SysUserService sysUserService;



    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 实现本地事务逻辑
        SysUserEntity sysUserEntity = (SysUserEntity) arg;
        try {
            sysUserService.saveUser(sysUserEntity);
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        // 根据消息内容检查本地事务状态，RocketMQ会自动回调此方法
        // 这里可以根据消息体中的业务标识去检查数据库操作是否成功
        // 返回事务状态
        return null;
    }
}