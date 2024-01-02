package com.netty.informationServe.service.messagedispatch;


import com.rose.common.mqutil.MqMessage;
import com.rose.common.mqutil.SendRequest;

/**
 * 消息分发接口定义
 */
public interface MessageDispatchService {
    void send(String instants, SendRequest request);
    void sendForSave( MqMessage request);
}
