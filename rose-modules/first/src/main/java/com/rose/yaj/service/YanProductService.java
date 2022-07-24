package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.entity.YanCategory;
import com.rose.yaj.entity.YanProduct;
import com.rose.yaj.util.PageQueryUtil;
import com.rose.yaj.util.PageResult;

/**
 * @author rose
 * @create 2022/6/18
 */
public interface YanProductService extends IService<YanProduct> {
    YanProduct getProductById(Long goodsId);

    PageResult searchProducts(PageQueryUtil pageUtil);
}
