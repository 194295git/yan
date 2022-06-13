package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.entity.YanAnswerComment;
import com.rose.yaj.entity.YanAnswerLike;

public interface YanAnswerLikeService  extends IService<YanAnswerLike> {
    void changeAnswerLikeStatus(String openid, String id);
}
