package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanCollegeEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:19:18
 */
public interface YanCollegeService extends IService<YanCollegeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

