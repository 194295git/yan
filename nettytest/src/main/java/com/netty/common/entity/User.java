package com.netty.common.entity;

import java.io.Serializable;

/**
 * @创建人 rose
 * @创建时间 2021/12/7
 * @描述 实体与数据库对象的对应表
 */
public class User implements Serializable {
    private static final long serialVersionUID = 478123456;

    // 用户id
    private Integer userId;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 性别
    private String sex;
    // 头像路径
    private String image;
    // 权限
    private boolean admin;
    // 描述
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
