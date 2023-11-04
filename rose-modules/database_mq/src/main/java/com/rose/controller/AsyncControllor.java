package com.rose.controller;

import com.rose.service.AsyncService;
import com.rose.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rose
 * @create 2023/7/17
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncControllor {

    @Autowired
    private AsyncService asyncMethodService;
    @Autowired
    private BusinessService businessService;

    @GetMapping("/demo")
    public String demo()  {
        log.info("接口调用：【开始】 --------------------");
        try {
            // 执行异步任务 - 自定义线程池
            asyncMethodService.asyncDemo1();
            asyncMethodService.asyncDemo2();
            asyncMethodService.asyncDemo3();
            // 执行异步任务 - 默认线程池
            businessService.asyncDemo4();
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception";
        }
        log.info("接口调用：【结束】 --------------------");
        return "success";
    }
}