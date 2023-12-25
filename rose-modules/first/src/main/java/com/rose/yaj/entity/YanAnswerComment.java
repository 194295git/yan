package com.rose.yaj.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yan_info_answer_comment")
public class YanAnswerComment {

    @TableId
    private Integer ansCommentId;

    private String answerId;

    private String relationId;

    private Integer isTop;

    private String comment;

    @TableLogic
    private Integer showStatus;

    private String openid;


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}
