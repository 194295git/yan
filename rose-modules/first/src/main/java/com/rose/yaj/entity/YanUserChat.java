package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YanUserChat {
    @TableId
    private Integer id;
    private String userOpenid;

    private String toOpenid;

    private String content;

    private Integer isRead;

    //新加群聊功能
    @TableField("`group`")
    private Boolean group;



    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
