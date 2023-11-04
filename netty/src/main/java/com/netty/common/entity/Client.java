package com.netty.common.entity;

import com.rose.common.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户端websocket连接对象属性描述
 */
@Data
@NoArgsConstructor
public class Client implements Serializable {

    //唯一标识
    private String channelId;

    //所连接的主机
    private String host;

    //上次活跃时间
    private String lastActiveTime;

    //创建时间
    private String createTime;

    public Client(String channelId, String host) {
        this.channelId = channelId;
        this.host = host;
        this.lastActiveTime = DateUtils.getCurrentDateTime();
        this.createTime = this.lastActiveTime;
    }
}
