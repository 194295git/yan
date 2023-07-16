package com.rose.yaj.controller;

import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.CartDto;
import com.rose.yaj.dto.OrderDto;
import com.rose.yaj.entity.YanOrderEntity;
import com.rose.yaj.entity.YanUserAddressEntity;
import com.rose.yaj.exeptionHandler.RRException;
import com.rose.yaj.service.YanCartService;
import com.rose.yaj.service.YanOrderService;
import com.rose.yaj.service.YanUserAddressService;
import com.rose.yaj.util.PageUtils;
import com.rose.yaj.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-20 15:01:04
 */
@RestController
@RequestMapping("/yaj/yan-order")
@Api(tags = "订单相关接口" , description = "YanOrderController | 订单模块")
public class YanOrderController {
    @Autowired
    private YanOrderService yanOrderService;
    @Autowired
    private YanCartService yanCartService;
    @Autowired
    private YanUserAddressService yanUserAddressService;

    /**
     * 保存
     */
    @PostMapping("/saveOrder")
    @ApiOperation(value = "生成订单接口", notes = "传参为地址id和待结算的购物项id数组")
    public GenericResponse save(@ApiParam(value = "订单参数") @RequestBody OrderDto.SaveOrderParam saveOrderParam,
                                HttpServletRequest request){
        String openid = Util.fromRequestToOpenid(request);
        int priceTotal = 0;
        if (saveOrderParam == null || saveOrderParam.getCartItemIds() == null || saveOrderParam.getAddressId() == null) {
            throw new RRException(ServiceError.PARAM_ERROR);

        }
        if (saveOrderParam.getCartItemIds().length < 1) {
            throw new RRException(ServiceError.PARAM_ERROR);
        }

        List<CartDto.CartItemVO> itemsForSave = yanCartService.getCartItemsForSettle(Arrays.asList(saveOrderParam.getCartItemIds()), openid);
        if(CollectionUtils.isEmpty(itemsForSave)){
            throw new RRException(ServiceError.PARAM_ERROR);
        }else{
            //总价
            for (CartDto.CartItemVO cartItemVO : itemsForSave) {
                //这块有个设计问题 BigDecimal与integer的相乘
                priceTotal +=  cartItemVO.getGoodsCount() *Integer.parseInt(cartItemVO.getSellingPrice().toString()) ;
            }
            YanUserAddressEntity address = yanUserAddressService.getById(saveOrderParam.getAddressId());
            //保存订单并返回订单号
            String saveOrderResult = yanOrderService.saveOrder(openid, address, itemsForSave);
            return GenericResponse.response(ServiceError.NORMAL,saveOrderResult);

        }

//        return GenericResponse.response(ServiceError.NORMAL);
    }

    /**
     * 列表
     */
    @ApiOperation("按参数查询展示")
    @GetMapping("/list")
    public GenericResponse list(@RequestParam Map<String, Object> params){
        PageUtils page = yanOrderService.queryPage(params);
        return GenericResponse.response(ServiceError.NORMAL,page);
    }


    /**
     * 信息
     */
    @ApiOperation("根据id来获取信息")
    @GetMapping("/info/{orderId}")
    public GenericResponse info(@PathVariable("orderId") Long orderId){
		YanOrderEntity yanOrder = yanOrderService.getById(orderId);

        return GenericResponse.response(ServiceError.NORMAL,yanOrder);
    }



    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    public GenericResponse update(@RequestBody YanOrderEntity yanOrder){
		yanOrderService.updateById(yanOrder);

        return GenericResponse.response(ServiceError.NORMAL);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    public GenericResponse delete(@RequestBody Long[] orderIds){
		yanOrderService.removeByIds(Arrays.asList(orderIds));
        return GenericResponse.response(ServiceError.NORMAL);

    }

}
