package com.rose.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.adaptor.PageUtils;
import com.rose.entity.YanDbTableEntity;


import java.util.Map;

/**
 * @author rose
 * @create 2022/11/21
 */
public interface YanDbTableService extends IService<YanDbTableEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

