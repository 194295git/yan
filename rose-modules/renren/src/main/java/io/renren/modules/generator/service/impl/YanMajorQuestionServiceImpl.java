package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.YanMajorQuestionDao;
import io.renren.modules.generator.entity.YanMajorQuestionEntity;
import io.renren.modules.generator.service.YanMajorQuestionService;


@Service("yanMajorQuestionService")
public class YanMajorQuestionServiceImpl extends ServiceImpl<YanMajorQuestionDao, YanMajorQuestionEntity> implements YanMajorQuestionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanMajorQuestionEntity> page = this.page(
                new Query<YanMajorQuestionEntity>().getPage(params),
                new QueryWrapper<YanMajorQuestionEntity>()
        );

        return new PageUtils(page);
    }

}