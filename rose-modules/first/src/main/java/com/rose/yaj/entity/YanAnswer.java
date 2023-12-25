package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "yan_info_answer")
public class YanAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "answer_id", type = IdType.ID_WORKER_STR)
    private String answerId;

    private Integer queId;

    private String openid;

    private String answerName;

    private String answerDes;

    private String answerContent;

    private Integer answerLike;

    //这个属性是当查询需要的添加的属性，并不是
    @TableField(exist = false)
    private String avatarUrl;


}
