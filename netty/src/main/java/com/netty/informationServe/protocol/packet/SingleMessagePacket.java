package com.netty.informationServe.protocol.packet;

import com.netty.informationServe.protocol.Packet;
import com.netty.informationServe.protocol.commond;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述
 */
public class SingleMessagePacket extends Packet {
    private String toUserId;

    private String message;

    private String fileType;

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
        return commond.SINGLE_MESSAGE;
    }
}
