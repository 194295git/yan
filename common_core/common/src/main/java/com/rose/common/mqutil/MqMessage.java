package com.rose.common.mqutil;

/**
 * @创建人 rose
 * @创建时间 2021/12/10
 * @描述 返回前端的消息主题与消息队列中储存的消息
 */
public class MqMessage {
    // 发送方id
    private String fromId;

    // 接收方id
    private String toId;

    // 消息内容
    private String infoContent;

    // 消息类型 在线或离线
    private String type;

    // 消息状态 在线或离线 1为私聊 0为群聊
    private Boolean state;

    public String gettTpye() {
        return tTpye;
    }

    public void settTpye(String tTpye) {
        this.tTpye = tTpye;
    }

    private String tTpye;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    private String token;


    private String msgid;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    //发送时间
    private String time;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
