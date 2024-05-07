//package com.rose.loginUser.sys.config;/**
// * @author rose
// * @create 2024/5/7
// */
//
//import com.rose.loginUser.sys.listener.UserRegistrationTransactionListener;
//import org.apache.rocketmq.client.exception.MQBrokerException;
//import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author <br>
// */
//@Configuration
//public class RocketMQConfig {
//
//    // 在RocketMQTemplate的配置中注册
//    @Autowired
//    private UserRegistrationTransactionListener transactionListener;
//
//    @Bean
//    public RocketMQTemplate rocketMQTemplate(RocketMQProperties properties, MyTransactionListener listener) {
//        RocketMQTemplate template = new RocketMQTemplate(properties);
//        template.setTransactionListener(transactionListener);
//        return template;
//    }
////    @Bean
////    public RocketMQTemplate rocketMQTemplate() throws MQBrokerException {
////        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate(rocketMQProperties);
////        rocketMQTemplate.setTransactionListener();
////        // 如果有其他自定义配置，可以在这里添加
////        return rocketMQTemplate;
////    }
//}
