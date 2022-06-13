package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.entity.YanMajor;
import com.rose.yaj.entity.YanMajorMe;
import com.rose.yaj.mapper.YanMajorMapper;
import com.rose.yaj.mapper.YanMajorMeMapper;
import com.rose.yaj.service.YanMajorMeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YanMajorMeServiceImpl extends ServiceImpl<YanMajorMeMapper, YanMajorMe> implements YanMajorMeService {
    @Override
    public List<YanMajorMe> getMajorMe() {
        return this.list(new QueryWrapper<YanMajorMe>());
    }
}
