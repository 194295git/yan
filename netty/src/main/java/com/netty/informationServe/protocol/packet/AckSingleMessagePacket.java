package com.netty.informationServe.protocol.packet;

import com.netty.informationServe.protocol.Packet;
import com.rose.common.netty.Commond;
import com.rose.common.base.WebsocketMessage;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述
 */
public class AckSingleMessagePacket extends Packet {

    private WebsocketMessage websocketMessage;
    private String toUserId;

    private String message;

    private String fileType;

    public WebsocketMessage getWebsocketMessage() {
        return websocketMessage;
    }

    public void setWebsocketMessage(WebsocketMessage websocketMessage) {
        this.websocketMessage = websocketMessage;
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
