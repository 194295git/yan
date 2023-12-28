package com.rose.service.impl;

/**
 * @author rose
 * @create 2023/7/17
 */

import com.rose.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步线程 - 执行业务
 * 注意：
 *  1.@Async 注解调用用线程池，不指定的话默认：SimpleAsyncTaskExecutor
 *  2.SimpleAsyncTaskExecutor 不是真的线程池，这个类不重用线程，默认每次调用都会创建一个新的线程
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {
    /**
     * 方法1：@Async 标注为异步任务：执行此方法的时候，会单独开启线程来执行，不影响主线程的执行
     */
    @Async("asyncExecutor")
    public void asyncDemo1() {
        log.info("asyncDemo1：" + Thread.currentThread().getName() + " 正在执行 ----------");
        // 故意等10秒，那么异步线程开起来，这样明显看到：方法2不用等方法1执行完就调用了
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncDemo1：" + Thread.currentThread().getName() + " 执行结束！！");
    }

    /**
     * 方法2：与方法1一起执行，证明2个线程异步执行，互不干扰
     */
    @Async("asyncExecutor")
    public void asyncDemo2() {
        log.info("asyncDemo2：" + Thread.currentThread().getName() + " 正在执行 ----------");
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncDemo2：" + Thread.currentThread().getName() + " 执行结束！！");
    }

    /**
     * 方法3：没有指定线程池，验证默认线程池也ok（不推荐：规避资源耗尽的风险）
     */
    @Async
    public void asyncDemo3() {
        log.info("asyncDemo3：" + Thread.currentThread().getName() + " 正在执行 ----------");
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncDemo3：" + Thread.currentThread().getName() + " 执行结束！！");
    }
}