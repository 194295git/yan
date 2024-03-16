package com.netty.informationServe.protocol.packet;

import com.netty.informationServe.protocol.Packet;
import com.rose.common.netty.Commond;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 群消息发送
 */
public class GroupMessagePacket extends Packet {
    //变成群之后就是一个人对一个群发的消息 现在还没有弄@ 的功能
    //但是我的groupId就是integer 所以这个地方就是Integer
    private Integer toGroupId;

    private String message;

    private String fileType;

    public Integer getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(Integer toGroupId) {
        this.toGroupId = toGroupId;
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
        return Commond.GROUP_MESSAGE;
    }
}
