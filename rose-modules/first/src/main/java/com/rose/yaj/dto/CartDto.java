package com.rose.yaj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author rose
 * @create 2022/6/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CartDto {

    @Data
    public static class SaveCartItemParam implements Serializable {

        @ApiModelProperty("商品数量")
        private Integer goodsCount;

        @ApiModelProperty("商品id")
        private Long goodsId;
    }
    @Data
    public static class UpdateCartItemParam implements Serializable {

        @ApiModelProperty("购物项id")
        private Long cartItemId;

        @ApiModelProperty("商品数量")
        private Integer goodsCount;
    }


    @Data
    public static class CartItemVO implements Serializable {

        @ApiModelProperty("购物项id")
        private Long cartItemId;

        @ApiModelProperty("商品id")
        private Long goodsId;

        @ApiModelProperty("商品数量")
        private Integer goodsCount;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品图片")
        private String goodsCoverImg;

        @ApiModelProperty("商品价格")
        private BigDecimal sellingPrice;
    }
}
