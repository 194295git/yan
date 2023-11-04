//package com.netty.informationServe.config;
//
//import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.client.naming.NacosNamingService;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author rose
// * @create 2023/10/30
// */
//@Configuration
//@AllArgsConstructor
//public class NacosNamingServiceConfig {
//
//    private final NacosDiscoveryProperties nacosDiscoveryProperties;
//
//    @Bean
//    public NacosNamingService nacosNamingService() {
//        try {
//
//            NacosNamingService service = new NacosNamingService(nacosDiscoveryProperties.getNacosProperties());
//            return service;
//        } catch (NacosException e) {
//            e.printStackTrace();

//        }
//        return null;
//    }
//}
