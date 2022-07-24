package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanCategoryEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-05-23 13:13:58
 */
public interface YanCategoryService extends IService<YanCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

