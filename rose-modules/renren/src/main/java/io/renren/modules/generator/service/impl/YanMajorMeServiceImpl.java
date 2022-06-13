package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.YanMajorMeDao;
import io.renren.modules.generator.entity.YanMajorMeEntity;
import io.renren.modules.generator.service.YanMajorMeService;


@Service("yanMajorMeService")
public class YanMajorMeServiceImpl extends ServiceImpl<YanMajorMeDao, YanMajorMeEntity> implements YanMajorMeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanMajorMeEntity> page = this.page(
                new Query<YanMajorMeEntity>().getPage(params),
                new QueryWrapper<YanMajorMeEntity>()
        );

        return new PageUtils(page);
    }

}