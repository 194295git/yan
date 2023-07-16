/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.loginUser.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 系统配置信息
 *
 * @author Mark 1942951600@qq.com
 */
@Data
@TableName("sys_config")
public class SysConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * key
	 */
	private String paramKey;
	/**
	 * value
	 */
	private String paramValue;
	/**
	 * 状态   0：隐藏   1：显示
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 父id
	 */
	private Long pid;

}
