package com.rose.yaj.service;


import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.feign.dto.RegisterFeign;

/**
 * 微信业务接口
 */
public interface WeChatService {

    /**
     * 已经废弃.
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    GenericResponse loginByWeb(String email,String password) throws Exception;

    GenericResponse registByWeb(String email, String password);

    GenericResponse registByOpenid(RegisterFeign dto);
}
