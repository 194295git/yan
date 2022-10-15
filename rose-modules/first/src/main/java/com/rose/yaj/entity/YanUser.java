package com.rose.yaj.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rosejava
 * @since 2020-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yan_user")
public class YanUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表id
     */
    @TableId(type = IdType.UUID)
    private String openid;

    //希望这个地方自己开的与数据库大小写转换
    private Integer chatId;

    /**
     * 用户名
     */
    private String username;

    private String password;

    private Integer isAlive;

    private String email;

    private String avatarUrl;


    private String major;

    private String college;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}
