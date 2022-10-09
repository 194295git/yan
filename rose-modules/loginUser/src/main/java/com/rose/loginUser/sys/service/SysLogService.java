/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.loginUser.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.common.utils.PageUtils;
import com.rose.loginUser.sys.entity.SysLogEntity;


import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark 1942951600@qq.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
