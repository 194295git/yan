package com.rose.yaj.controller.perm;

import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.util.Util;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rose
 * @create 2023/1/29
 */
@RestController
@RequestMapping("/yaj/yan-prem")
public class TestPermController {

    @RequiresPermissions("yan:common:zero")
    @PostMapping("/common")
    public GenericResponse commonPerm() throws Exception {


        return GenericResponse.response(ServiceError.NORMAL);


    }
    @RequiresPermissions("yan:common:first")
    @PostMapping("/coupon")
    public GenericResponse couponPerm() throws Exception {


        return GenericResponse.response(ServiceError.NORMAL);


    }
}
