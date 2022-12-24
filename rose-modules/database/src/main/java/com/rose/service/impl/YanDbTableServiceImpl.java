package com.rose.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.rose.adaptor.PageUtils;
import com.rose.adaptor.Query;
import com.rose.dao.YanDbTableDao;
import com.rose.entity.YanDbTableEntity;
import com.rose.service.YanDbTableService;


import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author rose
 * @create 2022/11/21
 */
@Service("yanDbTableService")
public class YanDbTableServiceImpl extends ServiceImpl<YanDbTableDao, YanDbTableEntity> implements YanDbTableService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanDbTableEntity> page = this.page(
                new Query<YanDbTableEntity>().getPage(params),
                new QueryWrapper<YanDbTableEntity>()
        );

        return new PageUtils(page);
    }

}