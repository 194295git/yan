package com.rose.yaj.listener;


import com.rabbitmq.client.Channel;

import com.rose.yaj.dto.ChatDto;
import com.rose.common.to.mq.Message2;
import com.rose.yaj.service.YanUserChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>Title: OrderSecKillListener</p>
 * Description：
 * date：2020/7/9 20:23
 */
@Slf4j
@RabbitListener(queues = "yan.netty.queue")
@Component
public class YanChatListener {


	@Autowired
	YanUserChatService yanUserChatService;

	@RabbitHandler
	public void listener(Message2 message1, Channel channel, Message message) throws IOException {
		try {

			ChatDto chatDto = new ChatDto();
			chatDto.setContent(message1.getInfoContent());
			chatDto.setToOpenid(message1.getToId());
			chatDto.setGroup(message1.getState());
			if (message1.getState() !=null){
				if(message1.getType().equals("onLine")){
					yanUserChatService.saveChat(message1.getFromId(),chatDto,1);
				}else {
					yanUserChatService.saveChat(message1.getFromId(),chatDto,0);
				}
			}


			channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
			log.info("ack成功");
		} catch (Exception e) {
			channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
		}
	}
}
