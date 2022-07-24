package com.rose.yaj.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.dto.CartDto;
import com.rose.yaj.entity.YanOrderEntity;
import com.rose.yaj.entity.YanUserAddressEntity;
import com.rose.yaj.util.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-20 15:01:04
 */
public interface YanOrderService extends IService<YanOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    String saveOrder(String openid, YanUserAddressEntity address, List<CartDto.CartItemVO> itemsForSave);
}

