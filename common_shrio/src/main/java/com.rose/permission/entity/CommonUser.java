package com.rose.shrio.entity;



import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author rosejava
 * @since 2020-10-01
 */

public class CommonUser implements Serializable {

    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long userId;

}
