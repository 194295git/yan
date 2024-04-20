package com.rose.loginUser.sys.controller;

import com.rose.common.base.GenericResponse;
import com.rose.common.base.ServiceError;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 目前用于netty模块的websocket鉴权
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class ChatAuthController {


    private Logger logger = LoggerFactory.getLogger(ChatAuthController.class);


    /**
     * 触发异常的话会使用全局异常管理系统.返回
     * {
     *   "msg": "token失效，请重新登录",
     *   "code": 401
     * }
     * 后续需要完善一下。以及退出机制.
     * @return
     */
    @RequestMapping(value = "/issuccess")
    public GenericResponse getAuth() {
        log.info("走shiro的验证方法");
        return GenericResponse.response(ServiceError.NORMAL );
    }

}
