package com.netty.common.domain;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 用户消息实习类
 */
public class User {
    // 用户openid
    private String openid;
//    // 用户id
//    private Integer userId;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 性别
    private String sex;
    // 头像路径
    private String image;

    private String loginStatus;



    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

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

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}
