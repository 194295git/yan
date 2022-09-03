package com.rose.loginUser.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 单点登录服务器
 *
 * @author stylefeng
 * @Date 2018年2月3日15:32:21
 */
@SpringBootApplication
//@MapperScan(basePackages = "com.rose.loginUser")
@EnableAspectJAutoProxy
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }
}