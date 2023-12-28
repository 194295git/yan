package com.rose.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author rose
 * @create 2023/7/17
 */
// com.example.async.service 为即将开启异步线程业务的包位置（后面有详细讲解）
@Slf4j
@EnableAsync
@Configuration
@ComponentScan("com.rose.service")
public class AsyncConfiguration implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(11);
        executor.setThreadNamePrefix("MyExecutor-");
        executor.initialize();
        return executor;
//————————————————
//        版权声明：本文为CSDN博主「小杨同学~」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/weixin_38937840/article/details/128097330
//        return executor();
   }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            /**
             * Handle the given uncaught exception thrown from an asynchronous method.
             *
             * @param ex     the exception thrown from the asynchronous method
             * @param method the asynchronous method
             * @param params the parameters used to invoked the method
             */
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                log.error("异步处理异常 方法为:{},参数为:{},异常为:{}", method, params, ex);
            }
        };
    }
}