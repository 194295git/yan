package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.dto.ProductDto;
import com.rose.yaj.entity.YanGroup;
import com.rose.yaj.entity.YanCategory;

import java.util.List;

/**
 * @author rose
 * @create 2022/6/9
 */
public interface  YanCategoryService extends IService<YanCategory> {
    List<ProductDto> list();
}
