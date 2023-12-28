package com.netty.informationServe.service.messagedispatch;


import com.netty.common.entity.SendRequest;

/**
 * 消息分发接口定义
 */
public interface MessageDispatchService {
    void send(String instants, SendRequest request);
}
