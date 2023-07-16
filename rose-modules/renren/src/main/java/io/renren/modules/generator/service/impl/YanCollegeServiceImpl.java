package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.common.utils.PageUtils;
import com.rose.common.utils.Query;
import io.renren.modules.generator.dao.YanCollegeDao;
import io.renren.modules.generator.entity.YanCollegeEntity;
import io.renren.modules.generator.service.YanCollegeService;
import org.springframework.stereotype.Service;

import java.util.Map;


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