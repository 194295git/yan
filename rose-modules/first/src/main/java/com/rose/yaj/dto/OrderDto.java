package com.rose.yaj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author rose
 * @create 2022/6/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderDto {

    @Data
    public static class SaveOrderParam implements Serializable {

        @ApiModelProperty("订单项id数组")
        private Long[] cartItemIds;

        @ApiModelProperty("地址id")
        private Long addressId;
    }


}
