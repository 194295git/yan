package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YanAnswerLike {

    @TableId
    private Integer likeId;

    private String likeUserId;

    private String likeAnswerId;

    private Integer status;
}
