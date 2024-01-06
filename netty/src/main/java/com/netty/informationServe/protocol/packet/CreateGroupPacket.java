package com.netty.informationServe.protocol.packet;

import com.netty.informationServe.protocol.Packet;
import com.rose.common.netty.Commond;

import java.util.List;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 创建群消息
 */
public class CreateGroupPacket extends Packet {
    /**
     * 创建群里需要把群里的Id发过来
     */
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    private List<String> userIdList;

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    @Override
    public Byte getCommand() {
        return Commond.CREATE_GROUP;
    }
}
