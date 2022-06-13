package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.entity.YanQuestionTag;
import com.rose.yaj.entity.YanUser;
import com.rose.yaj.mapper.YanQuestionTagMapper;
import com.rose.yaj.mapper.YanUserMapper;
import com.rose.yaj.service.YanQuestionTagService;
import org.springframework.stereotype.Service;

@Service
public class YanQuestionTagServiceImpl extends ServiceImpl<YanQuestionTagMapper, YanQuestionTag> implements YanQuestionTagService {
    @Override
    public YanQuestionTag getQueTagById() {
        return null;
    }
}
