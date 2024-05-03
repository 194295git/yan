package com.rose.yaj.listener;/**
 * @author rose
 * @create 2024/4/22
 */

import com.rose.common.mqutil.SendRequest;
import com.rose.yaj.config.RoseFeignConfig;
import com.rose.yaj.feign.NettyMqFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author <br>
 */

@Slf4j
@Component
public class AsyncSend {

    @Autowired
    NettyMqFeign nettyMqFeign;




    public CompletableFuture<Void> pushMessageList(List<SendRequest> send) {
        log.info("进来执行这块代码");
        return CompletableFuture.runAsync(() -> {
            // 调用Feign客户端执行同步HTTP请求
            //设置过滤应该有的token
//            RoseFeignConfig.token.set(token);
            nettyMqFeign.sendList(send);
            
        });
    }

    public CompletableFuture<Void> pushMessage(String token ,SendRequest send) {
        log.info("进来执行这块代码");
        return CompletableFuture.runAsync(() -> {
            // 调用Feign客户端执行同步HTTP请求
            //设置过滤应该有的token
            RoseFeignConfig.token.set(token);
            nettyMqFeign.send(send);

        });
    }
}

