package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.dto.CartDto;
import com.rose.yaj.entity.YanShopCart;
import com.rose.yaj.util.PageQueryUtil;
import com.rose.yaj.util.PageResult;

import java.io.Serializable;
import java.util.List;

/**
 * @author rose
 * @create 2022/6/20
 */
public interface YanCartService  extends IService<YanShopCart> {
    String saveCartItem(CartDto.SaveCartItemParam saveCartItemParam, String openid);

    String updateNewBeeMallCartItem(CartDto.UpdateCartItemParam updateCartItemParam, String openid);

    /**
     * 获取购物项详情
     * @param cartItemId
     * @return
     */
    YanShopCart getCartItemById(Long cartItemId);

    /**
     * 删除购物车的商品
     * @param cartItemId
     * @param openid
     * @return
     */
    Boolean deleteById(Long cartItemId, String openid);

    PageResult getMyCartItems(PageQueryUtil pageUtil);


    List<CartDto.CartItemVO> getCartItemsForSettle(List<Long> cartItemIds, String openid);
}
