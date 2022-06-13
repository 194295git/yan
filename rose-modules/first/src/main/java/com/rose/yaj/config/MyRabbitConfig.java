package com.rose.yaj.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>Title: MyRabbitConfig</p>
 * Description：配置序列化方式
 * date：2020/6/29 14:29
 */
@Slf4j
@Configuration
public class MyRabbitConfig {

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Bean
	public MessageConverter messageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	/**
	 * 1.设置确认回调： ConfirmCallback
	 * 先在配置文件中开启 publisher-confirms: true
	 * @PostConstruct: MyRabbitConfig对象创建完成以后 执行这个方法
	 *
	 *  2.消息抵达队列的确认回调
	 * 　	开启发送端消息抵达队列确认
	 *     publisher-returns: true
	 *     	只要抵达队列，以异步优先回调我们这个 returnconfirm
	 *     template:
	 *       mandatory: true
	 *	3.消费端确认(保证每一个消息被正确消费才可以broker删除消息)
	 *		1.默认是自动确认的 只要消息接收到 服务端就会移除这个消息
	 *
	 *		如何签收:
	 *			签收: channel.basicAck(deliveryTag, false);
	 *			拒签: channel.basicNack(deliveryTag, false,true);
	 *	配置文件中一定要加上这个配置
	 *		listener:
	 *       simple:
	 *         acknowledge-mode: manual
	 */

	/**
	 * 定制rabbitTemplate @PostConstruct在构造器创建完之后再来调用这个方法
	 */
	@PostConstruct
	public void initRabbitTemplate(){
		//设置确认回调
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			/**
			 * 只要正确的抵达服务器，那么这块的回调就是true
			 * @param correlationData 当前消息的唯一关联数据
			 * @param b  ack
			 * @param s  cause
			 */
			@Override
			public void confirm(CorrelationData correlationData, boolean b, String s) {
				System.out.println("confirm correlationData "+ correlationData+"ack" +b);
			}
		});
		/**
		 * 当没有送达指定队列的时候才会触发这个回调
		 */
		rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
			@Override
			public void returnedMessage(Message message, int i, String s, String s1, String s2) {

			}
		});

	}

}

