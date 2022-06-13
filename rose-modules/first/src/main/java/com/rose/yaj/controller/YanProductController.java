package com.rose.yaj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.ProductDto;
import com.rose.yaj.entity.YanMajor;
import com.rose.yaj.service.YanMajorService;
import com.rose.yaj.service.YanProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rose
 * @create 2022/6/9
 */
@RestController
@RequestMapping("/yaj/yan-product")
@Api(tags = "产品相关接口" , description = "YanProductController | 产品模块")
public class YanProductController {


    @Autowired
    private YanProductService yanProductService;

    @ApiOperation("获取一级分类与二级分类")
    @GetMapping("/getCategory")
    public GenericResponse getCategory() throws Exception {

        List<ProductDto> list = yanProductService.list();
        return GenericResponse.response(ServiceError.NORMAL, list);
    }
}
