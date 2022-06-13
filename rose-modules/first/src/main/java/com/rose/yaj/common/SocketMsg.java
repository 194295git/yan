package com.rose.yaj.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocketMsg implements Serializable {


    private int type; //聊天类型0：群聊，1：单聊.

    // openid
    private String fromUser;//发送者.
    // openid
    private String toUser;//接受者.
    private String msg;//消息

}
