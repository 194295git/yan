package com.netty.informationServe.protocol.packet;

import com.netty.informationServe.protocol.Packet;
import com.rose.common.netty.Commond;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 单聊的packet的一些属性.
 */
public class SingleMessagePacket extends Packet {
    private String toUserId;

    private String message;

    private String fileType;

    private String msgid;

    private String isretry;

    public String getIsretry() {
        return isretry;
    }

    public void setIsretry(String isretry) {
        this.isretry = isretry;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public Byte getCommand() {
        return Commond.SINGLE_MESSAGE;
    }
}
