package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.common.utils.PageUtils;
import com.rose.common.utils.Query;
import io.renren.modules.generator.dao.YanAnswerDao;
import io.renren.modules.generator.entity.YanAnswerEntity;
import io.renren.modules.generator.service.YanAnswerService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("yanAnswerService")
public class YanAnswerServiceImpl extends ServiceImpl<YanAnswerDao, YanAnswerEntity> implements YanAnswerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanAnswerEntity> page = this.page(
                new Query<YanAnswerEntity>().getPage(params),
                new QueryWrapper<YanAnswerEntity>()
        );

        return new PageUtils(page);
    }

}