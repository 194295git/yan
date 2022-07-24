package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author rose
 * @create 2022/6/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YanShopCart {

    @TableId(value = "cart_item_id", type = IdType.AUTO)
    private Long cartItemId;

    private String openid;

    private Long goodsId;

    private Integer goodsCount;

    private Byte isDeleted;

    //这个地方让数据库自己是生成吧
    private Date createTime;

    private Date updateTime;
}
