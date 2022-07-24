package com.rose.yaj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.entity.YanOrderItemEntity;
import com.rose.yaj.util.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-21 18:03:59
 */
public interface YanOrderItemService extends IService<YanOrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

