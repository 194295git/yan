package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.entity.YanQuestionTag;
import com.rose.yaj.entity.YanUser;

public interface YanQuestionTagService extends IService<YanQuestionTag> {

    YanQuestionTag getQueTagById();

}
