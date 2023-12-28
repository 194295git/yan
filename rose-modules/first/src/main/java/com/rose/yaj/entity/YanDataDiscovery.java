package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName(value = "yan_info_data_discovery")
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
