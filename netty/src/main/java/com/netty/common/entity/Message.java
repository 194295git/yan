package com.netty.common.entity;

import java.io.Serializable;

/**
 * @创建人 rose
 * @创建时间 2021/12/10
 * @描述
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 478123456;

    // 发送方id
    private Integer fromId;

    // 接收方id
    private Integer toId;

    // 消息内容
    private String infoContent;

    // 消息类型 在线或离线
    private Boolean online;

    // 消息状态 群聊或私聊 1为私聊 0为群聊
    private Boolean singleMessage;

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Boolean getSingleMessage() {
        return singleMessage;
    }

    public void setSingleMessage(Boolean singleMessage) {
        this.singleMessage = singleMessage;
    }

    //发送时间
    private String time;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
