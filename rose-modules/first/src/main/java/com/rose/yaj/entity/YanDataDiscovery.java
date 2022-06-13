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
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YanDataDiscovery implements Serializable {

    private static final long serialVersionUID = 1L;

    //问题id
    private Integer questionId;

    //回答id
    private String answerId;

    // 图片
    private String feedSourceImg;

    // 问题
    private String question;

    // 裁剪过的回答
    private String answerCtntShort;

}
