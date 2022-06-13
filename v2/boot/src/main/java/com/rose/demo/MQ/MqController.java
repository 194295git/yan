package com.rose.demo.MQ;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;
@RestController
@RequestMapping("/mq")
//@Api(tags = "mq相关接口" , description = "MqController | 消息模块")
public class MqController {
    private static final Logger logger = LoggerFactory.getLogger(MqController.class);

    @Autowired
    private ProdServer prodServer;
    @Autowired
    WebSocketServer webSocketServer;

    @RabbitListener(queues = {FanoutRabbitConfig.DEFAULT_BOOK_QUEUE})
    public void listenerAutoAck(String text, Message message, Channel channel) {

        // TODO 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            logger.info("[消费者一监听的消息] - [{}]", text);
            ChatDto.MqChat chatDto = JSONObject.parseObject(text, ChatDto.MqChat.class);

            Integer rs = WebSocketServer.sendInfo(chatDto.getMessage(), chatDto.getToUserId());
//            yanUserChatService.saveChat(chatDto.getOpenid(),chatDto,rs);
//            webSocketServer.sendMqMessage(text);

            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // TODO 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }




    @RequestMapping(value="/direct/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String direct(String eventIds) {
        String msg = UUID.randomUUID().toString();
        try {
            prodServer.convertAndSend(msg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("-----------------mq添加出错------------");
        }
        return msg;
    }

    @RequestMapping(value="/fanout/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String fanout(String eventIds) {
        String msg = UUID.randomUUID().toString();
        try {
            prodServer.fanoutSendMsg(msg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("-----------------mq添加出错------------");
        }
        return msg;
    }

    @RequestMapping(value="/topic/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String topic(String eventIds) {
        String msg = UUID.randomUUID().toString();
        try {
            prodServer.topicSendMsg1(msg);
            prodServer.topicSendMsg2(msg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("-----------------mq添加出错------------");
        }
        return msg;
    }



}
