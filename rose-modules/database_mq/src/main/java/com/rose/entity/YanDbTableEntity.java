package com.rose.entity;

/**
 * @author rose
 * @create 2022/11/21
 */
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-11-20 17:26:00
 */
@Data
@TableName("yan_db_table")
public class YanDbTableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 字段类型
     */
    private String fieldtype;
    /**
     * 字段名
     */
    private String fieldname;
    /**
     * 长度
     */
    private Integer length;
    /**
     * 是否null
     是否null
     */
    private Integer isnull;
    /**
     * 小数点
     */
    private Integer decimalpoint;
    /**
     * 键
     */
    private String fieldkey;
    /**
     * 注释
     */
    private String fieldcomment;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;

}
