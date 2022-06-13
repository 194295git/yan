package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.dto.CommentList;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanAnswerComment;
import com.rose.yaj.mapper.YanAnswerCommentMapper;
import com.rose.yaj.mapper.YanAnswerMapper;
import com.rose.yaj.service.YanAnswerCommentService;
import com.rose.yaj.service.YanUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class YanAnswerCommentServiceImpl extends ServiceImpl<YanAnswerCommentMapper, YanAnswerComment> implements YanAnswerCommentService {

    @Autowired
    private YanUserService yanUserService;
    /**
     *
     * @param id
     * @param params isTop，comment，relationUser
     */
    @Override
    public void addComment(String id, String openid, Map<String, Object> params) {
        YanAnswerComment yanAnswerComment = new YanAnswerComment();
        yanAnswerComment.setAnswerId(id);
        yanAnswerComment.setOpenid(openid);
        yanAnswerComment.setShowStatus(0);

        yanAnswerComment.setComment((String) params.get("comment"));

        int isTop = Integer.parseInt(params.get("isTop").toString());
        yanAnswerComment.setIsTop(isTop);
        if (isTop != 1){
            yanAnswerComment.setRelationId((String) params.get("relationId"));
        }


        this.save(yanAnswerComment);

    }

    @Override
    public List<CommentList> getComment(Long id) {
        QueryWrapper<YanAnswerComment> wrapper = new QueryWrapper<YanAnswerComment>();
        wrapper.eq("answer_id", id);

        List<YanAnswerComment> yanAnswerComments = this.list(wrapper);
        List<CommentList> commentLists = new ArrayList<>();

        List<CommentList> list = yanAnswerComments.stream().filter(
                filter-> filter.getIsTop() == 1
        ).map((item) -> {
            CommentList commentList = new CommentList();
            commentList.setAvatarUrl(yanUserService.getAvatarUrlByOpenid(item.getOpenid()));
            BeanUtils.copyProperties(item, commentList);
            if (item.getIsTop() == 1) {
                commentLists.add(commentList);
                List<CommentList> commentsLeve2 = this.list(new QueryWrapper<YanAnswerComment>().eq("relation_id", item.getAnsCommentId())).stream().map(
                        (level2) -> {
                            CommentList commentListLeve2 = new CommentList();
                            commentListLeve2.setAvatarUrl(yanUserService.getAvatarUrlByOpenid(level2.getOpenid()));
                            BeanUtils.copyProperties(level2, commentListLeve2);
                            return commentListLeve2;
                        }
                ).collect(Collectors.toList());
                commentList.setLevel2(commentsLeve2);
            }
            return commentList;


        }).collect(Collectors.toList());

        return list;
    }
}
