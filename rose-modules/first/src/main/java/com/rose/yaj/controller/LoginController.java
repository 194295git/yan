package com.rose.yaj.controller;


import com.rose.yaj.annotation.LoginToken;
import com.rose.yaj.annotation.PassToken;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.UserDto;
import com.rose.yaj.service.WeChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "登录相关接口" , description = "LoginController | 登录模块")
public class LoginController {

    @Autowired
    private WeChatService weChatService;




    /**
     * 使用邮箱和密码登录
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    @ApiOperation("使用邮箱和密码注册")
    @PassToken
    @PostMapping("/registByWeb")
    public GenericResponse registByWeb(String email, String password) throws Exception {
        return weChatService.registByWeb(email,password);
    }

    @ApiOperation("使用邮箱和密码登录")
    @PassToken
    @PostMapping("/loginByWeb")
    public GenericResponse loginByWeb(
            @RequestBody UserDto.Login dto) throws Exception {
        return weChatService.loginByWeb(dto.getEmail(),dto.getPassword());
    }


    /**
     * 权限测试
     */
    @LoginToken
    @GetMapping("/test")

    public GenericResponse test() {
        return GenericResponse.response(ServiceError.NORMAL, "test");
    }

    @LoginToken
    @PostMapping("/test")
    public GenericResponse testPost() {
        return GenericResponse.response(ServiceError.NORMAL, "testPOST");
    }
    @LoginToken
    @GetMapping("/test/a")
    public GenericResponse testA() {
        return GenericResponse.response(ServiceError.NORMAL, "testManage");
    }

    @LoginToken
    @GetMapping("/hello")
    public GenericResponse hello() {
        return GenericResponse.response(ServiceError.NORMAL, "hello security");
    }


    @GetMapping("info")
    public GenericResponse info() {
        return GenericResponse.response(ServiceError.NORMAL, "info");
    }

}
