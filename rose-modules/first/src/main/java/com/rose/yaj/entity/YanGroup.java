package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author rose
 * @create 2022/6/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "yan_im_group")
public class YanGroup implements Serializable {

    @TableId(value = "group_id", type = IdType.AUTO)
    private Integer groupId;

    private String groupName;

    private String image;

    private String description;

    private String admin;
}
