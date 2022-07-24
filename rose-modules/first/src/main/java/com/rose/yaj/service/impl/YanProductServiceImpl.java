package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.entity.YanProduct;
import com.rose.yaj.exeptionHandler.RRException;
import com.rose.yaj.mapper.YanProductMapper;
import com.rose.yaj.service.YanProductService;
import com.rose.yaj.util.PageQueryUtil;
import com.rose.yaj.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rose
 * @create 2022/6/18
 */
@Service
public class YanProductServiceImpl extends ServiceImpl<YanProductMapper, YanProduct> implements YanProductService {

    @Autowired
    YanProductMapper productMapper;

    @Override
    public YanProduct getProductById(Long goodsId) {
        YanProduct yanProduct = this.getById(goodsId);
        if(yanProduct == null){
            throw new RRException(ServiceError.GOODS_NOT_EXIST);
        }
        return yanProduct;
    }

    @Override
    public PageResult searchProducts(PageQueryUtil pageUtil) {
        List<YanProduct> goodsList = productMapper.findYanGoodsListBySearch(pageUtil);
        int total = productMapper.getTotalYanMallGoods(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
