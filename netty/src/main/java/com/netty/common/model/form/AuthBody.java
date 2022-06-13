package com.netty.common.model.form;
/**
 * 用户登录对象
 * 
 *
 * @author STARS
 */
public class AuthBody {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

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
}
