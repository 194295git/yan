package com.rose.yaj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.entity.YanUserAddressEntity;
import com.rose.yaj.util.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-21 12:52:33
 */
public interface YanUserAddressService extends IService<YanUserAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

