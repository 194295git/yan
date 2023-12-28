package com.rose.service.impl;

import com.rose.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author rose
 * @create 2023/7/17
 */
@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    /**
     * 方法4：没有指定线程池，验证默认线程池也ok（不推荐：规避资源耗尽的风险）
     */
    @Async
    @Override
    public void asyncDemo4() {
        log.info("asyncDemo4：" + Thread.currentThread().getName() + " 正在执行 ----------");
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncDemo4：" + Thread.currentThread().getName() + " 执行结束！！");
    }
}
