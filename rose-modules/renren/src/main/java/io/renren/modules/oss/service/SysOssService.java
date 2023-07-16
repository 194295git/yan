/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.common.utils.PageUtils;
import io.renren.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author Mark 1942951600@qq.com
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
