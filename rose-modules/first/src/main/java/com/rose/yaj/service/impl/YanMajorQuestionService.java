package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.entity.YanMajor;
import com.rose.yaj.entity.YanMajorQuestion;
import com.rose.yaj.mapper.YanMajorMapper;
import com.rose.yaj.mapper.YanMajorQuestionMapper;
import com.rose.yaj.service.YanMajorService;
import org.springframework.stereotype.Service;

@Service
public class YanMajorQuestionService extends ServiceImpl<YanMajorQuestionMapper, YanMajorQuestion> implements com.rose.yaj.service.YanMajorQuestionService {
}
