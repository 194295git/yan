package com.netty.informationServe.service.messagedispatch;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.constants.Constants;
import com.netty.common.entity.SendRequest;
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

    private Message getInstants(String topic, SendRequest msg) {
        //构建message消息体
        Message message = new Message(RocketMQConfig.getWebsocketTopic(topic), JSONObject.toJSONString(msg).getBytes());
        //由调用接口的方式触发消息
        message.putUserProperty(Constants.Trigger, WebsocketMessage.Trigger.HTTP.code + "");
        return message;
    }
}
