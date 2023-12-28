package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "yan_info_question_tags")
public class YanQuestionTags  implements Serializable {


    @TableId
    private Integer id;

    private Integer queId;

    private Integer tagId;



}
