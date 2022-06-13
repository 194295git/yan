package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.dto.ProductDto;
import com.rose.yaj.entity.YanGroup;
import com.rose.yaj.entity.YanMajor;
import com.rose.yaj.entity.YanProduct;
import com.rose.yaj.mapper.YanGroupMapper;
import com.rose.yaj.mapper.YanProductMapper;
import com.rose.yaj.service.YanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rose
 * @create 2022/6/9
 */
@Service
public class YanProductServiceImpl extends ServiceImpl<YanProductMapper, YanProduct> implements YanProductService {



    @Override
    public List<ProductDto> list() {
        QueryWrapper<YanProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);

        List<YanProduct> list = this.list(queryWrapper);
        List<ProductDto> productDtos = list.stream().map((item) -> {
            ProductDto productDto = new ProductDto();
            productDto.setCategoryId(item.getId());
            productDto.setCategoryLevel(1);
            productDto.setCategoryName(item.getName());
            //查询二级分类
            QueryWrapper<YanProduct> query = new QueryWrapper<>();
            query.eq("parent_id", item.getId());
            List<YanProduct> listLevel = this.list(query);
            List<ProductDto.SecondLevelCategory> collect = listLevel.stream().map((level) -> {
                ProductDto.SecondLevelCategory secondLevelCategory = new ProductDto.SecondLevelCategory();
                secondLevelCategory.setCategoryId(level.getId());
                secondLevelCategory.setCategoryLevel(2);
                secondLevelCategory.setCategoryName(level.getName());
                secondLevelCategory.setParentId(level.getParentId());
                return secondLevelCategory;
            }).collect(Collectors.toList());
            //设置二级分类
            productDto.setSecondLevelCategoryVOS(collect);
            return productDto;


        }).collect(Collectors.toList());
        return productDtos;
    }
}
