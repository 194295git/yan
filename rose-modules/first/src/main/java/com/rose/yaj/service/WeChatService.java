package com.rose.yaj.service;


import com.rose.yaj.common.GenericResponse;

/**
 * 微信业务接口
 */
public interface WeChatService {


    GenericResponse loginByWeb(String email,String password) throws Exception;

    GenericResponse registByWeb(String email, String password);
}
