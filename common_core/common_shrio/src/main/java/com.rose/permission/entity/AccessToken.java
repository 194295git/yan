package com.rose.permission.entity;/**
 * @author rose
 * @create 2024/1/9
 */

import java.util.Date;

/**
 * @author rose<br>
 */
public class AccessToken {

    public AccessToken(String token, Date expirationDate) {
        this.token = token;
        ExpirationDate = expirationDate;
    }

    private String token;

    private Date ExpirationDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        ExpirationDate = expirationDate;
    }
}
