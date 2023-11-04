package com.netty.common.model.Result;

/**
 * @创建人 rose
 * @创建时间 2021/12/12
 * @描述 用户可以获得的好友信息
 * @author  rose
 */
public class Friend {
    // 用户id
    private Integer userId;
    // 用户名
    private String userName;
    // 性别
    private String sex;
    // 头像路径
    private String image;
    // 用户描述
    private String description;

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

    public String getDiscription() {
        return description;
    }

    public void setDiscription(String discription) {
        this.description = discription;
    }
}
