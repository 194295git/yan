package com.rose.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author rose
 * @create 2023/10/27
 */
@Slf4j
@Configuration
@EnableAsync
public class ExecutorConfig {
    private static String namePrefix = "async-javayh";

    @Bean(name = "asyncExecutor")
    public Executor asyncExamExecutor() {
        log.info("start asyncExamExecutor");
        //计算计算机的核数
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processors = Runtime.getRuntime().availableProcessors();
        //配置核心线程数
        executor.setCorePoolSize(processors * 5);
        //配置最大线程数
        executor.setMaxPoolSize(processors * 5);
        //配置队列大小
        executor.setQueueCapacity(1000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}


