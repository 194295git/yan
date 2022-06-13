package com.rose.demo.service;

import com.rose.demo.common.GenericResponse;

/**
 * @author rose
 * @create 2022/5/18
 */
public interface WeChatService {
    /**
     * 小程序登录
     *
     * @param code
     * @return
     */
    GenericResponse wxLogin(String code) throws Exception;

}
