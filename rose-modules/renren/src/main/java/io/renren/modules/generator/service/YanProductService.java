package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanProductEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-05-23 13:13:58
 */
public interface YanProductService extends IService<YanProductEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

