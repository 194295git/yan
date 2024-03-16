package com.netty.common.entity;

import java.io.Serializable;

/**
 * @创建人 rose
 * @创建时间 2021/12/25
 * @描述
 */
public class Group implements Serializable {
    private static final long serialVersionUID = 478123456;

    Integer groupId;

    String groupName;

    String image;

    String description;

    Integer admin;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
