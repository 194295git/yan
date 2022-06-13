package com.rose.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author rose
 * @create 2022/5/18
 */
@Data
public class User {

    private static final long serialVersionUID = 1L;

    private String openid;

    private Integer chatId;

    private String username;

    private String password;

    private Integer isAlive;

    private String email;

    private String avatarUrl;


    private String major;

    private String college;

    private Date createTime;

    private Date updateTime;
}
