package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.dto.CommentList;
import com.rose.yaj.entity.YanAnswerComment;

import java.util.List;
import java.util.Map;

public interface YanAnswerCommentService  extends IService<YanAnswerComment> {
    void addComment(String id, String openid, Map<String, Object> params);

    List<CommentList> getComment(Long id);
}
