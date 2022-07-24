package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.common.utils.Query;
import com.rose.yaj.entity.YanOrderItemEntity;
import com.rose.yaj.mapper.YanOrderItemMapper;
import com.rose.yaj.service.YanOrderItemService;
import com.rose.yaj.util.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service("yanOrderItemService")
public class YanOrderItemServiceImpl extends ServiceImpl<YanOrderItemMapper, YanOrderItemEntity> implements YanOrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanOrderItemEntity> page = this.page(
                new Query<YanOrderItemEntity>().getPage(params),
                new QueryWrapper<YanOrderItemEntity>()
        );

        return new PageUtils(page);
    }

}