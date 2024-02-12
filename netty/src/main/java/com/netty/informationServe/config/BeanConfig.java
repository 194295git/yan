package com.netty.informationServe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * EnableScheduling 注解 开启定时任务的支持.
 * @author rose
 */
@Configuration
//@EnableScheduling
public class BeanConfig {
    /**
     * 把 RestTemplate 注册到 Spring 中
     * 需要什么就返回什么
     * 这里需要注册 RestTemplate 那么就把它返回
     * @return
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}