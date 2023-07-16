package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanMajorQuestionEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:28:50
 */
public interface YanMajorQuestionService extends IService<YanMajorQuestionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

