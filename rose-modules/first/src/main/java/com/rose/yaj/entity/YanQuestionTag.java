package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YanQuestionTag  implements Serializable {


    @TableId
    private Integer tagId;

    private String queTag;


}
