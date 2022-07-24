package com.rose.yaj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.yaj.entity.YanShopCart;
import com.rose.yaj.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author rose
 * @create 2022/6/20
 */
@Mapper
public interface YanShopCartMapper extends BaseMapper<YanShopCart> {
    YanShopCart selectByOpenidAndProductId(@Param("openid") String openid,@Param("goodsId") Long goodsId);

    int selectCountByOpenid(String openid);

    int insertSelective(YanShopCart yanShopCart);

    YanShopCart selectByPrimaryKey(Long cartItemId);

    int updateByPrimaryKeySelective(YanShopCart tempCart);

    int deleteByPrimaryKey(Long cartItemId);

    List<YanShopCart> findCartItems(PageQueryUtil pageUtil);

    int getTotalCartItems(PageQueryUtil pageUtil);

    List<YanShopCart> selectByUserIdAndCartItemIds(@Param("openid")String openid,@Param("cartItemIds") List<Long> cartItemIds);

    int deleteBatch(List<Long> itemIdList);
}
