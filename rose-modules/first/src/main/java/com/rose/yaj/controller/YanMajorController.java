package com.rose.yaj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.entity.YanMajor;
import com.rose.yaj.service.YanMajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rosejava
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/yaj/yan-major")
@Api(tags = "专业相关接口" , description = "YanMajorController | 专业模块")
public class YanMajorController {
    @Autowired
    private YanMajorService yanMajorService;

    @ApiOperation("根据id获取专业")
    @GetMapping("/getMajor")
    public GenericResponse getAnswer(@RequestParam("id") String id) throws Exception {
        System.out.println("测试是否发送求情");
        QueryWrapper<YanMajor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        List<YanMajor> list = yanMajorService.list(queryWrapper);
        return GenericResponse.response(ServiceError.NORMAL, list);
    }

}

