package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-21 18:03:59
 */
@Data
@TableName("yan_order_item")
public class YanOrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单关联购物项主键id
	 */
	@TableId
	private Long orderItemId;
	/**
	 * 订单主键id
	 */
	private Long orderId;
	/**
	 * 关联商品id
	 */
	private Long goodsId;
	/**
	 * 下单时商品的名称(订单快照)
	 */
	private String goodsName;
	/**
	 * 下单时商品的主图(订单快照)
	 */
	private String goodsCoverImg;
	/**
	 * 下单时商品的价格(订单快照)
	 */
	private Integer sellingPrice;
	/**
	 * 数量(订单快照)
	 */
	private Integer goodsCount;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
