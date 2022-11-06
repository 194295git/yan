package com.rose.file.controller;

import com.rose.file.util.R;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rose
 * @create 2022/10/23
 */
@RestController
@RequestMapping("test")
public class TestShrio {

    /**
     * 测试是否可以获取权限.
     */
    @GetMapping("/needPermission")
    @RequiresPermissions("sys:user:list")
    public R needPermission(){
        return R.ok().put("page", "file getPermission");
    }

    /**
     * 测试取得认证.
     */
    @GetMapping("/needAuthentication")
    public R needAuthentication(){
        return R.ok().put("page", "file getneedAuthentication");
    }
}
