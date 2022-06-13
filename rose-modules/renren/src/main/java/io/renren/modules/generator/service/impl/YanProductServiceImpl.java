package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.YanProductDao;
import io.renren.modules.generator.entity.YanProductEntity;
import io.renren.modules.generator.service.YanProductService;


@Service("yanProductService")
public class YanProductServiceImpl extends ServiceImpl<YanProductDao, YanProductEntity> implements YanProductService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanProductEntity> page = this.page(
                new Query<YanProductEntity>().getPage(params),
                new QueryWrapper<YanProductEntity>()
        );

        return new PageUtils(page);
    }

}