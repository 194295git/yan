/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.loginUser.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.common.base.GenericResponse;
import com.rose.loginUser.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author Mark 1942951600@qq.com
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	GenericResponse createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
