package com.rose.yaj.service;


import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.feign.dto.RegisterFeign;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 微信业务接口
 */
public interface WeChatService {


    GenericResponse loginByWeb(String email,String password) throws Exception;

    GenericResponse registByWeb(String email, String password);

    GenericResponse registByOpenid(RegisterFeign dto);
}
