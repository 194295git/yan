package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.entity.YanQuestionTag;
import com.rose.yaj.entity.YanQuestionTags;
import com.rose.yaj.mapper.YanQuestionTagMapper;
import com.rose.yaj.mapper.YanQuestionTagsMapper;
import com.rose.yaj.service.YanQuestionTagService;
import com.rose.yaj.service.YanQuestionTagsService;
import org.springframework.stereotype.Service;

@Service
public class YanQuestionTagsServiceImpl extends ServiceImpl<YanQuestionTagsMapper, YanQuestionTags> implements YanQuestionTagsService {
}