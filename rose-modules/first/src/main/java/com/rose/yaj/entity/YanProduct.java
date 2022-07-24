package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rose
 * @create 2022/6/18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class YanProduct implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer categoryId;

    @TableField("name")
    private String name;

    private String image;

    private BigDecimal price;

    private String detail;

    private Integer stock;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date updateTime;



}
