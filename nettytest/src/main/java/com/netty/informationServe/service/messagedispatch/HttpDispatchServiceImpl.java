//package com.netty.informationServe.service.messagedispatch;
//
//
//import com.rose.common.mqutil.SendRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * 基于http的形式，分发消息给对应客户端所在的websocket服务器
// */
//@Service
//@Slf4j
//public class HttpDispatchServiceImpl implements MessageDispatchService {
//
//    private static ExecutorService service = Executors.newCachedThreadPool();
//
//    @Override
//    public void send(String instance, SendRequest request) {
//        service.execute(() -> {
//            try {
//                String url = "http://"+instance+"/message/send";
//                RestTemplate restTemplate = new RestTemplate();
//                restTemplate.postForEntity(url,request, Response.class);
//            }catch (Exception e){
//                log.error("发送失败,host="+instance,e);
//            }
//        });
//    }
//}
