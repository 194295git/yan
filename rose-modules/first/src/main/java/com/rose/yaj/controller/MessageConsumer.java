//package com.rose.yaj.controller;
//
//import com.alibaba.fastjson.JSON;
//
//
//import com.rose.yaj.dto.ChatDto;
//import com.rose.yaj.dto.Message;
//import com.rose.yaj.service.YanUserChatService;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @创建人 rose
// * @创建时间 2021/12/8
// * @描述 消息队列的消息
// */
//
//@Component
//@RocketMQMessageListener(
//        topic = "NettyInfo",
//        consumerGroup = "NettyInfo",
//        selectorExpression = "*"
//)
//public class MessageConsumer implements RocketMQListener<Object> {
//
//    @Autowired
//    YanUserChatService yanUserChatService;
//
//    @Override
//    public void onMessage(Object message) {
//        System.out.println(message);
//        Message message1 =  JSON.parseObject((String) message, Message.class);
//        ChatDto chatDto = new ChatDto();
//        chatDto.setContent(message1.getInfoContent());
//        chatDto.setToOpenid(message1.getToId());
//        chatDto.setGroup(message1.getState());
//        if (message1.getState() !=null){
//            if(message1.getType().equals("onLine")){
//                yanUserChatService.saveChat(message1.getFromId(),chatDto,1);
//            }else {
//                yanUserChatService.saveChat(message1.getFromId(),chatDto,0);
//            }
//        }
//    }
//}