package com.netty.informationServe.service.messagedispatch;

import com.alibaba.fastjson.JSONObject;
import com.rose.common.mqutil.MqMessage;
import com.rose.common.constant.NettyConstants;
import com.rose.common.mqutil.SendRequest;
import com.netty.informationServe.config.RocketMQConfig;
import com.rose.common.base.WebsocketMessage;
import io.github.rhwayfun.springboot.rocketmq.starter.common.DefaultRocketMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 基于MQ的形式，分发消息给对应客户端所在的websocket服务器所订阅的topic
 * @author rose
 */
@Service
@Slf4j
public class MQDispatchServiceImpl implements MessageDispatchService {

    @Resource
    private DefaultRocketMqProducer producer;

    @Override
    public void send(String instants, SendRequest request) {
        producer.sendMsg(getInstants(instants,  request));
    }

    @Override
    public void sendForSave( MqMessage request) {
        producer.sendMsg(getInstantsSaveDB(request));
    }

    @Override
    public void sendAck(String instants, SendRequest request) {

    }

    /**
     * 构建消息请求体.
     * @param topic
     * @param msg
     * @return
     */
    private Message getInstants(String topic, SendRequest msg) {
        //构建message消息体
        Message message = new Message(RocketMQConfig.getWebsocketTopic(topic), JSONObject.toJSONString(msg).getBytes());
        //由调用接口的方式触发消息
        message.putUserProperty(NettyConstants.Trigger, WebsocketMessage.Trigger.HTTP.code + "");
        return message;
    }

    /**
     * 构造保存消息.
     * @param msg
     * @return
     */
    private Message getInstantsSaveDB( MqMessage msg) {
        //构建message消息体
        Message message = new Message(NettyConstants.ROCKETMQ_TPOIC_SAVECHAT, JSONObject.toJSONString(msg).getBytes());
        //由调用接口的方式触发消息
        message.putUserProperty(NettyConstants.Trigger, WebsocketMessage.Trigger.HTTP.code + "");
        return message;
    }


}
