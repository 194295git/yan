package com.rose.loginUser.sys.controller;

import com.rose.loginUser.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author rose
 * @create 2022/10/20
 */
@RestController
@RequestMapping("/test")
public class TestPathController {

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params){


        return R.ok().put("page", "123");
    }
    /**
     * 测试是否可以获取权限.
     */
    @GetMapping("/needPermission")
    @RequiresPermissions("sys:user:list")
    public R needPermission(){
        return R.ok().put("page", "getPermission");
    }

    /**
     * 测试取得认证.
     */
    @GetMapping("/needAuthentication")
    public R needAuthentication(){
        return R.ok().put("page", "getneedAuthentication");
    }

}
