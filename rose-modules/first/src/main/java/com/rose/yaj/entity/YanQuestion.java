package com.rose.yaj.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rosejava
 * @since 2020-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YanQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "question_id", type = IdType.AUTO)
    private Integer questionId;

    private String openid;

    private String queTitle;

    private String queContent;

    private Integer queTagId;


    private Integer queView;

    // 这个是需要后续操作的
    private Integer queComment;

    private String queAnswerListId;

    // 图片
    private String feedSourceImg;


}
