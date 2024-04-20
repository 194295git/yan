package com.netty.informationServe.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author rose
 * @create 2024/1/6
 */

@Configurable
@Slf4j
public class RoseFeignConfig implements RequestInterceptor {

    /**
     * 当前使用数据源标识
     */
    public static ThreadLocal<String> token  = new ThreadLocal<String>();


//   @Bean
//    public RequestInterceptor requestInterceptor() {
//        System.out.println("n1");
//        return requestTemplate -> {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            if (null != attributes) {
//                HttpServletRequest request = attributes.getRequest();
//                log.info("Feign request: {}", request.getRequestURI());
//                // 将token信息放入header中
//                System.out.println(token);
//                requestTemplate.header("token",token);
//            }
//        };
//    }

    @Bean
    Logger.Level roseFeignConfigLog(){
        //这里记录所有，根据实际情况选择合适的日志level
        return Logger.Level.FULL;
    }
    //请求头携带token
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Content-Type", "application/json");
        requestTemplate.header("token", token.get());
    }
}
