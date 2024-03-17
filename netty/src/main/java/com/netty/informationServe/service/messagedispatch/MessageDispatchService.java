package com.netty.informationServe.service.messagedispatch;


import com.rose.common.mqutil.MqMessage;
import com.rose.common.mqutil.SendRequest;

/**
 * 消息分发接口定义
 */
public interface MessageDispatchService {
    /**
     * 发送普通聊天消息
     * @param instants 主题
     * @param request 内容
     */
    void send(String instants, SendRequest request);

    /**
     * 发送给first微服务用来存储聊天消息
     * @param request
     */
    void sendForSave(String topic , MqMessage request);

    /**
     * 为了更新状态
     * @param topic
     * @param msgid
     */
    void sendForUpdate(String topic , String msgid);

    void sendAck(String instants, SendRequest request);

}
