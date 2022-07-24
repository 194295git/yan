package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.common.Constants;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.CartDto;
import com.rose.yaj.entity.YanProduct;
import com.rose.yaj.entity.YanShopCart;
import com.rose.yaj.exeptionHandler.RRException;
import com.rose.yaj.mapper.YanShopCartMapper;
import com.rose.yaj.mapper.YanProductMapper;
import com.rose.yaj.service.YanCartService;
import com.rose.yaj.util.PageQueryUtil;
import com.rose.yaj.util.PageResult;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author rose
 * @create 2022/6/20
 */
@Service
public class YanCartServiceImpl  extends ServiceImpl<YanShopCartMapper,YanShopCart> implements YanCartService {

    @Autowired
    YanShopCartMapper yanCartMapper;

    @Autowired
    YanProductMapper yanProductMapper;



    @Override
    public String saveCartItem(CartDto.SaveCartItemParam saveCartItemParam, String openid) {
        YanShopCart tempCart = yanCartMapper.selectByOpenidAndProductId(openid,saveCartItemParam.getGoodsId());
        if (tempCart != null) {
            //已存在则修改该记录
            throw new RRException(ServiceError.SHOPPING_CART_ITEM_EXIST_ERROR);
        }
        YanProduct yanProduct = yanProductMapper.selectById(saveCartItemParam.getGoodsId());
        if (yanProduct == null) {
            return ServiceError.GOODS_NOT_EXIST.getMsg();
        }
        int totalItem = yanCartMapper.selectCountByOpenid(openid);
        //小于单个商品的最大数量
        if (saveCartItemParam.getGoodsCount() < 1) {
            return ServiceError.SHOPPING_CART_ITEM_NUMBER_ERROR.getMsg();
        }
        //超出单个商品的最大数量
        if (saveCartItemParam.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceError.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getMsg();
        }
        //超出最大数量
        if (totalItem > Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER) {
            return ServiceError.SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR.getMsg();
        }
        YanShopCart yanShopCart = new YanShopCart();
        yanShopCart.setGoodsId(saveCartItemParam.getGoodsId());
        yanShopCart.setGoodsCount(saveCartItemParam.getGoodsCount());
        yanShopCart.setOpenid(openid);
        int res = yanCartMapper.insertSelective(yanShopCart);
        if (res > 0) {
            return ServiceError.NORMAL.getMsg();

        }
        return ServiceError.DB_ERROR.getMsg();

    }

    @Override
    public String updateNewBeeMallCartItem(CartDto.UpdateCartItemParam updateCartItemParam, String openid) {
        YanShopCart tempCart = yanCartMapper.selectByPrimaryKey(updateCartItemParam.getCartItemId());
        if(tempCart ==null){
            return ServiceError.DATA_NOT_EXIST.getMsg();
        }
        //根据购物车商品所属于的openid 必须与当前登录的opeind是一致的
        if(!tempCart.getOpenid().equals(openid)){
            return ServiceError.REQUEST_FORBIDEN_ERROR.getMsg();
        }
        //超出单个商品的最大数量
        if (updateCartItemParam.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceError.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getMsg();
        }
        //数值相同，则不执行数据操作
        if (updateCartItemParam.getGoodsCount().equals(tempCart.getGoodsCount())) {
            return ServiceError.NORMAL.getMsg();
        }
        tempCart.setGoodsCount(updateCartItemParam.getGoodsCount());
        tempCart.setUpdateTime(new Date());
        if (yanCartMapper.updateByPrimaryKeySelective(tempCart) > 0) {
            return ServiceError.NORMAL.getMsg();
        }
        return ServiceError.DB_ERROR.getMsg();
    }

    @Override
    public YanShopCart getCartItemById(Long cartItemId) {
        YanShopCart yanShopCart = yanCartMapper.selectByPrimaryKey(cartItemId);
        if(yanShopCart ==null){
            throw  new RRException(ServiceError.DATA_NOT_EXIST);

        }

        return yanShopCart;
    }

    @Override
    public Boolean deleteById(Long cartItemId, String openid) {
        YanShopCart yanShopCart = yanCartMapper.selectByPrimaryKey(cartItemId);
        if(yanShopCart ==null){
            return false;

        }
        //openid不同不能删除
        if (!openid.equals(yanShopCart.getOpenid())) {
            return false;
        }
        return yanCartMapper.deleteByPrimaryKey(cartItemId) > 0;
    }

    @Override
    public PageResult getMyCartItems(PageQueryUtil pageUtil) {
        List<CartDto.CartItemVO> vos = new ArrayList<>();
        List<YanShopCart> cartItems =yanCartMapper.findCartItems(pageUtil);
        int total = yanCartMapper.getTotalCartItems(pageUtil);
        List<CartDto.CartItemVO> res= getShoppingCartItemVOS(vos,cartItems);
        PageResult pageResult = new PageResult(res,total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public List<CartDto.CartItemVO> getCartItemsForSettle(List<Long> cartItemIds, String openid) {
        List<CartDto.CartItemVO> vos = new ArrayList<>();
        if (CollectionUtils.isEmpty(cartItemIds)) {
            throw new RRException(ServiceError.SHOPPING_CART_NOT_EXIST);
        }
        List<YanShopCart> shoppingCartItems = yanCartMapper.selectByUserIdAndCartItemIds(openid, cartItemIds);
        if (CollectionUtils.isEmpty(shoppingCartItems)) {
            throw new RRException(ServiceError.SHOPPING_CART_NOT_EXIST);
        }
        if(shoppingCartItems.size() != cartItemIds.size()){
            throw new RRException(ServiceError.PARAM_ERROR);
        }
        List<CartDto.CartItemVO> res = getShoppingCartItemVOS(vos,shoppingCartItems);

        return res;
    }


    private List<CartDto.CartItemVO> getShoppingCartItemVOS(List<CartDto.CartItemVO> vos, List<YanShopCart> cartItems) {
        if(!CollectionUtils.isEmpty(cartItems)){
            List<Long> collect = cartItems.stream().map(YanShopCart::getGoodsId).collect(Collectors.toList());
            List<YanProduct>  products  = yanProductMapper.selectByPrimaryKeys(collect);
            Map<Integer, YanProduct> productMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(products)){
                productMap = products.stream().collect(Collectors.toMap(YanProduct::getId, Function.identity(), (entity1, entity2) -> entity1));

            }
            for (YanShopCart cartItem : cartItems) {
                CartDto.CartItemVO cartItemVO = new CartDto.CartItemVO();
                BeanUtils.copyProperties(cartItem, cartItemVO);
                if(productMap.containsKey(cartItem.getGoodsId())){
                    YanProduct yanProductTemp = productMap.get(cartItem.getGoodsId());
                    cartItemVO.setGoodsCoverImg(yanProductTemp.getImage());
                    cartItemVO.setGoodsName(yanProductTemp.getName());
                    //商品价格有点定义的类型不一样
                    cartItemVO.setSellingPrice(yanProductTemp.getPrice());
                    vos.add(cartItemVO);
                }

            }

        }
        return vos;

    }


}
