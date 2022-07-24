package com.rose.yaj.controller;

import com.rose.yaj.common.Constants;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.CartDto;
import com.rose.yaj.entity.YanShopCart;
import com.rose.yaj.service.YanCartService;
import com.rose.yaj.util.PageQueryUtil;
import com.rose.yaj.util.PageResult;
import com.rose.yaj.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rose
 * @create 2022/6/20
 */
@RestController
@RequestMapping("/yaj/yan-cart")
@Api(tags = "购物车相关接口" , description = "YanCartController | 购物车模块")
public class YanCartController {

    @Autowired
    YanCartService yanCartService;


    @PostMapping("/addCart")
    @ApiOperation(value = "添加商品到购物车接口", notes = "传参为商品id、数量")
    public GenericResponse saveNewBeeMallShoppingCartItem(@RequestBody CartDto.SaveCartItemParam saveCartItemParam,
                                                          HttpServletRequest request) {
        String openid = Util.fromRequestToOpenid(request);
        String saveResult = yanCartService.saveCartItem(saveCartItemParam, openid);
        //添加成功
        if(ServiceError.NORMAL.getMsg().equals(saveResult)){
            return GenericResponse.response(ServiceError.NORMAL);
        }

        //添加失败
        return GenericResponse.response(false,500,saveResult,null);

    }
    @PutMapping("/updateCart")
    @ApiOperation(value = "修改购物项数据", notes = "传参为购物项id、数量")
    public GenericResponse updateNewBeeMallShoppingCartItem(@RequestBody CartDto.UpdateCartItemParam updateCartItemParam,
                                                            HttpServletRequest request) {
        String openid = Util.fromRequestToOpenid(request);
        String updateResult = yanCartService.updateNewBeeMallCartItem(updateCartItemParam, openid);
        if(ServiceError.NORMAL.getMsg().equals(updateResult)){
            return GenericResponse.response(ServiceError.NORMAL);
        }

        //添加失败
        return GenericResponse.response(false,500,updateResult,null);

    }


    @DeleteMapping("/delete/{cartItemId}")
    @ApiOperation(value = "删除购物项", notes = "传参为购物项id")
    public GenericResponse updateNewBeeMallShoppingCartItem(@PathVariable("cartItemId") Long cartItemId,
                                                            HttpServletRequest request) {
        String openid = Util.fromRequestToOpenid(request);
        YanShopCart yanShopCart = yanCartService.getCartItemById(cartItemId);
        if (!openid.equals(yanShopCart.getOpenid())) {
            return GenericResponse.response(ServiceError.REQUEST_FORBIDEN_ERROR);
        }
        Boolean deleteResult = yanCartService.deleteById(cartItemId,openid);
        //删除成功
        if (deleteResult) {
            return GenericResponse.response(ServiceError.NORMAL);
        }
        //删除失败
        return GenericResponse.response(ServiceError.OPERATE_ERROR);

    }


    @GetMapping("/shop-cart/page")
    @ApiOperation(value = "购物车列表(每页默认5条)", notes = "传参为页码")
    public  GenericResponse cartItemPageList(Integer pageNumber,  HttpServletRequest request) {
        Map params = new HashMap(8);
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        String openid = Util.fromRequestToOpenid(request);
        params.put("openid", openid);
        params.put("page", pageNumber);
        params.put("limit", Constants.SHOPPING_CART_PAGE_LIMIT);
        //封装分页请求参数
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        PageResult  res =yanCartService.getMyCartItems(pageUtil);
        return GenericResponse.response(ServiceError.NORMAL,res);


    }
}
