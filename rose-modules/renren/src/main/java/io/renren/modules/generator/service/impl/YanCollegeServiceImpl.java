package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.YanCollegeDao;
import io.renren.modules.generator.entity.YanCollegeEntity;
import io.renren.modules.generator.service.YanCollegeService;


@Service("yanCollegeService")
public class YanCollegeServiceImpl extends ServiceImpl<YanCollegeDao, YanCollegeEntity> implements YanCollegeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanCollegeEntity> page = this.page(
                new Query<YanCollegeEntity>().getPage(params),
                new QueryWrapper<YanCollegeEntity>()
        );

        return new PageUtils(page);
    }

}