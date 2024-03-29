package com.rose.loginUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author rose
 * @create 2022/10/7
 */
@EnableFeignClients(basePackages = "com.rose.loginUser.sys.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class LoginUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginUserApplication.class, args);
    }



}