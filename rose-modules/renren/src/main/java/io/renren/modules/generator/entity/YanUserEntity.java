package io.renren.modules.generator.entity;

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
 * @date 2021-08-26 22:19:18
 */
@Data
@TableName("yan_user")
public class YanUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户表id
	 */
	@TableId
	private String openid;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 头像地址
	 */
	private String avatarUrl;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 会员等级id
	 */
	private Long levelId;
	/**
	 * 是否登录
	 */
	private Integer isAlive;
	/**
	 * 积分
	 */
	private Integer integration;
	/**
	 * 成长值
	 */
	private Integer growth;
	/**
	 * 我心怡的大学
	 */
	private String college;
	/**
	 * 我的专业
	 */
	private String major;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后一次更新时间
	 */
	private Date updateTime;

}
