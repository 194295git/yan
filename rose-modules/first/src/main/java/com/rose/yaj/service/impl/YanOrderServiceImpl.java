package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.common.utils.Query;
import com.rose.yaj.common.Constants;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.CartDto;
import com.rose.yaj.dto.StockNumDTO;
import com.rose.yaj.entity.YanOrderEntity;
import com.rose.yaj.entity.YanOrderItemEntity;
import com.rose.yaj.entity.YanProduct;
import com.rose.yaj.entity.YanUserAddressEntity;
import com.rose.yaj.exeptionHandler.RRException;
import com.rose.yaj.mapper.YanOrderMapper;
import com.rose.yaj.mapper.YanProductMapper;
import com.rose.yaj.mapper.YanShopCartMapper;
import com.rose.yaj.service.YanOrderItemService;
import com.rose.yaj.service.YanOrderService;
import com.rose.yaj.util.BeanUtil;
import com.rose.yaj.util.NumberUtil;
import com.rose.yaj.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service("yanOrderService")
public class YanOrderServiceImpl extends ServiceImpl<YanOrderMapper, YanOrderEntity> implements YanOrderService {


    @Autowired
    YanProductMapper yanProductMapper;

    @Autowired
    YanShopCartMapper yanShopCartMapper;

    @Autowired
    YanOrderItemService yanOrderItemService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanOrderEntity> page = this.page(
                new Query<YanOrderEntity>().getPage(params),
                new QueryWrapper<YanOrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public String saveOrder(String openid, YanUserAddressEntity address, List<CartDto.CartItemVO> cartItems) {
        List<Long> itemIdList = cartItems.stream().map(CartDto.CartItemVO::getCartItemId).collect(Collectors.toList());
        List<Long> goodIds = cartItems.stream().map(CartDto.CartItemVO::getGoodsId).collect(Collectors.toList());
        List<YanProduct> yanProducts = yanProductMapper.selectByPrimaryKeys(goodIds);
        //检查是否包含已下架内容
        List<YanProduct> goodsListNotSelling = yanProducts.stream()
                .filter(newBeeMallGoodsTemp -> newBeeMallGoodsTemp.getStatus() != Constants.SELL_STATUS_UP)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(goodsListNotSelling)) {
            //goodsListNotSelling 对象非空则表示有下架商品
            throw new RRException(goodsListNotSelling.get(0).getName() + "已下架，无法生成订单");

        }
        Map<Integer, YanProduct> productMap  = yanProducts.stream().collect(Collectors.toMap(YanProduct::getId, Function.identity(), (entity1, entity2) -> entity1));

        //判断商品库存
        for (CartDto.CartItemVO cartItem : cartItems) {
            if (!productMap.containsKey(cartItem.getGoodsId())){
                throw new RRException("购物车数据异常！");
            }
            //存在数量大于库存的情况，直接返回错误提醒
            if (cartItem.getGoodsCount() > productMap.get(cartItem.getGoodsId()).getStock()) {
                throw new RRException(ServiceError.SHOPPING_ITEM_COUNT_ERROR);
            }

        }
        //删除购物项
        if (!CollectionUtils.isEmpty(itemIdList) && !CollectionUtils.isEmpty(goodIds) && !CollectionUtils.isEmpty(yanProducts)) {
            int res = yanShopCartMapper.deleteBatch(itemIdList);
            if(res > 0){
                //更新库存数量
                List<StockNumDTO> stockNumDTOS =  BeanUtil.copyList(cartItems, StockNumDTO.class);
                int updateStockNumResult = yanProductMapper.updateStockNum(stockNumDTOS);
                if (updateStockNumResult < 1) {
                    throw new RRException(ServiceError.SHOPPING_ITEM_COUNT_ERROR);
                }
                //生成订单号
                String orderNo = NumberUtil.genOrderNo();
                int priceTotal = 0;
                YanOrderEntity yanOrderEntity = new YanOrderEntity();
                yanOrderEntity.setOrderNo(orderNo);
                yanOrderEntity.setOpenid(openid);
                for (CartDto.CartItemVO cartItem : cartItems) {
                    priceTotal += cartItem.getGoodsCount() *Integer.parseInt(cartItem.getSellingPrice().toString());
                }
                yanOrderEntity.setTotalPrice(priceTotal);
                String extraInfo = "";
                yanOrderEntity.setExtraInfo(extraInfo);

                //生成订单项并保存订单项纪录
                boolean save = this.save(yanOrderEntity);
                if(save ==true){
                    //生成订单收货地址快照，并保存至数据库
                    YanUserAddressEntity yanUserAddressEntity = new YanUserAddressEntity();

                    List<YanOrderItemEntity> orderItems = new ArrayList<>();
                    for (CartDto.CartItemVO cartItem : cartItems) {
                        YanOrderItemEntity yanOrderItemEntity = new YanOrderItemEntity();
                        BeanUtil.copyProperties(cartItem, yanOrderItemEntity);
                        yanOrderItemEntity.setOrderId(yanOrderEntity.getOrderId());
                        orderItems.add(yanOrderItemEntity);

                    }
                    //保存到数据库
                    boolean b = yanOrderItemService.saveBatch(orderItems);
                    if( b == true ){
                        return orderNo;
                    }
                    throw new RRException(ServiceError.ORDER_PRICE_ERROR);

                }
                throw new RRException(ServiceError.DB_ERROR);

            }
            throw new RRException(ServiceError.DB_ERROR);

        }

        //生成订单
        return ServiceError.SHOPPING_ITEM_ERROR.getMsg();
    }

}