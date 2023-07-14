package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.common.utils.PageUtils;
import com.rose.common.utils.Query;
import io.renren.modules.generator.dao.YanQuestionTagDao;
import io.renren.modules.generator.entity.YanQuestionTagEntity;
import io.renren.modules.generator.service.YanQuestionTagService;
import org.springframework.stereotype.Service;

import java.util.Map;


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