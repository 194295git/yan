package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.YanUserDao;
import io.renren.modules.generator.entity.YanUserEntity;
import io.renren.modules.generator.service.YanUserService;


@Service("yanUserService")
public class YanUserServiceImpl extends ServiceImpl<YanUserDao, YanUserEntity> implements YanUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanUserEntity> page = this.page(
                new Query<YanUserEntity>().getPage(params),
                new QueryWrapper<YanUserEntity>()
        );

        return new PageUtils(page);
    }

}