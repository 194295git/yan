package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.common.utils.Query;
import com.rose.yaj.entity.YanUserAddressEntity;
import com.rose.yaj.mapper.YanUserAddressMapper;
import com.rose.yaj.service.YanUserAddressService;
import com.rose.yaj.util.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;



@Service("yanUserAddressService")
public class YanUserAddressServiceImpl extends ServiceImpl<YanUserAddressMapper, YanUserAddressEntity> implements YanUserAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanUserAddressEntity> page = this.page(
                new Query<YanUserAddressEntity>().getPage(params),
                new QueryWrapper<YanUserAddressEntity>()
        );

        return new PageUtils(page);
    }

}