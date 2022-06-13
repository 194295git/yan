package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.YanQuestionTagDao;
import io.renren.modules.generator.entity.YanQuestionTagEntity;
import io.renren.modules.generator.service.YanQuestionTagService;


@Service("yanQuestionTagService")
public class YanQuestionTagServiceImpl extends ServiceImpl<YanQuestionTagDao, YanQuestionTagEntity> implements YanQuestionTagService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanQuestionTagEntity> page = this.page(
                new Query<YanQuestionTagEntity>().getPage(params),
                new QueryWrapper<YanQuestionTagEntity>()
        );

        return new PageUtils(page);
    }

}