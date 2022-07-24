package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-06-20 15:01:04
 */
@Data
@TableName("yan_order")
public class YanOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单表主键id
	 */
	@TableId
	private Long orderId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 用户主键id
	 */
	private String openid;
	/**
	 * 订单总价
	 */
	private Integer totalPrice;
	/**
	 * 支付状态:0.未支付,1.支付成功,-1:支付失败
	 */
	private Integer payStatus;
	/**
	 * 0.无 1.支付宝支付 2.微信支付
	 */
	private Integer payType;
	/**
	 * 支付时间
	 */
	private Date payTime;
	/**
	 * 订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭
	 */
	private Integer orderStatus;
	/**
	 * 订单body
	 */
	private String extraInfo;
	/**
	 * 删除标识字段(0-未删除 1-已删除)
	 */
	private Integer isDeleted;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Date createTime;
	/**
	 * 最新修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Date updateTime;

}
