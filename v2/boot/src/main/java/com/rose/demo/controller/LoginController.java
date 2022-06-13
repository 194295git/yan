package com.rose.demo.controller;

import com.rose.demo.common.GenericResponse;
import com.rose.demo.common.ServiceError;
import com.rose.demo.dto.UserDto;
import com.rose.demo.service.LoginService;
import com.rose.demo.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author rose
 * @create 2022/5/18
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;


    @Autowired
    private WeChatService weChatService;
    @PostMapping("/login")
    public GenericResponse login(String code) throws Exception {
        System.out.println("测试是否发送求情");
        System.out.println("测试是否have code " + code);
        return weChatService.wxLogin(code);
    }

    @PostMapping("/user/register")
    public GenericResponse registByWeb(@RequestBody UserDto.register param) {
        return  loginService.regist(param.getEmail(),param.getPassword(),param.getUsername());
    }

}
