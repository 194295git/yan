/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.loginUser.sys.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.loginUser.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author Mark 1942951600@qq.com
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
