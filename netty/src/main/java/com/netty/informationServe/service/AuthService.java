package com.netty.informationServe.service;/**
 * @author rose
 * @create 2024/4/17
 */

import com.netty.informationServe.config.RoseFeignConfig;
import com.netty.informationServe.feign.NettyMqFeign;
import com.rose.common.base.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author rose<br>
 */
@Slf4j
@Service
public class AuthService {

    @Autowired
    private NettyMqFeign authClient; // Feign客户端



    public CompletableFuture<Boolean> authenticateAsync(String token) {
        log.info("进来执行这块代码");
        return CompletableFuture.supplyAsync(() -> {
            // 调用Feign客户端执行同步HTTP请求
            RoseFeignConfig.token.set(token);
            GenericResponse rs = authClient.getAuth();
            log.info(rs.toString());
            return rs.getStatusCode() == 200;
        });
    }

}
