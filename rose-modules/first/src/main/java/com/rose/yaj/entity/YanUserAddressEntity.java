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
 * @date 2022-06-21 12:52:33
 */
@Data
@TableName("yan_user_address")
public class YanUserAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long addressId;
	/**
	 * 用户主键id
	 */
	private String openid;
	/**
	 * 收货人姓名
	 */
	private String userName;
	/**
	 * 收货人手机号
	 */
	private String userPhone;
	/**
	 * 是否为默认 0-非默认 1-是默认
	 */
	private Integer defaultFlag;
	/**
	 * 省
	 */
	private String provinceName;
	/**
	 * 城
	 */
	private String cityName;
	/**
	 * 区
	 */
	private String regionName;
	/**
	 * 收件详细地址(街道/楼宇/单元)
	 */
	private String detailAddress;
	/**
	 * 删除标识字段(0-未删除 1-已删除)
	 */
	private Integer isDeleted;
	/**
	 * 添加时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
