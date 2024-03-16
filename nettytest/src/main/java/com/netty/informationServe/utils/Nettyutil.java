package com.netty.informationServe.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author rose
 * @create 2023/10/31
 */
@Slf4j
@Component
public class Nettyutil {
    @Value("${server.port}")
    public String port;



    public  String getInstance(){
        String instanceid = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            instanceid = localHost.getHostAddress()+":"+port;
        }catch (Exception e){
            log.info("获取当前地址失败");
        }
        return instanceid;
    }
}
