package com.rose.file.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rose
 * @create 2022/10/12
 */
@RestController
public class TestPort {

    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/getPort")
    public String getServerPort(){
        return "调用端口 " + serverPort + " 成功！";
    }

}
