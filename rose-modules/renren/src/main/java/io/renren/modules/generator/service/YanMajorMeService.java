package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanMajorMeEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:28:50
 */
public interface YanMajorMeService extends IService<YanMajorMeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

