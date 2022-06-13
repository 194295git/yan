package com.rose.yaj.dto;

import com.rose.yaj.entity.YanAnswerComment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentList {

    private Integer ansCommentId;

    //回答的id
    private String answerId;

    //评论内容
    private String comment;

    // 评论人id
    private String openid;

    private String avatarUrl;

    private List<CommentList> level2;
}
