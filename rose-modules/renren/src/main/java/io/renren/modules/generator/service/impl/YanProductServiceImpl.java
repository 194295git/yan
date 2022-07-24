package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.YanCategoryDao;
import io.renren.modules.generator.entity.YanCategoryEntity;
import io.renren.modules.generator.service.YanCategoryService;


@Service("YanCategoryService")
public class YanCategoryServiceImpl extends ServiceImpl<YanCategoryDao, YanCategoryEntity> implements YanCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<YanCategoryEntity> page = this.page(
                new Query<YanCategoryEntity>().getPage(params),
                new QueryWrapper<YanCategoryEntity>()
        );

        return new PageUtils(page);
    }

}