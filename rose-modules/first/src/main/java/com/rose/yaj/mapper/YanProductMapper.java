package com.rose.yaj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.yaj.dto.StockNumDTO;
import com.rose.yaj.entity.YanProduct;
import com.rose.yaj.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author rose
 * @create 2022/6/18
 */
public interface YanProductMapper  extends BaseMapper<YanProduct> {
    List<YanProduct> findYanGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalYanMallGoods(PageQueryUtil pageUtil);

    List<YanProduct> selectByPrimaryKeys(List<Long> collect);

    int updateStockNum(@Param("stockNumDTOS") List<StockNumDTO> stockNumDTOS);
}
