package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yan_info_answer_like")
public class YanAnswerLike {

    @TableId
    private Integer likeId;

    private String likeUserId;

    private String likeAnswerId;

    private Integer status;
}
