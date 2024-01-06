package com.rose.common.netty;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 协议栈中的各个指令与其对应 也可以用枚举类型
 */
public interface Commond {
    //自己发送的消息确认
    Byte SELF_RESPONSE = 0;
    //私聊消息
    Byte SINGLE_MESSAGE = 1;
    //私聊响应
    Byte SINGLE_MESSAGE_RESPONSE = -1;

    //私聊响应
    Byte SINGLE_MESSAGE_OTHER = 2;
    //创建群
    Byte CREATE_GROUP = 3;
    //创建群响应
    Byte CREATE_GROUP_RESPONSE = 4;
    //登录请求
    Byte LOGIN_REQUEST = 5;
    // 登录响应
    Byte LOGIN_RESPONSE = 6;
    //注册请求
    Byte REGISTER = 7;
    //注册响应
    Byte REGISTER_RESPONSE = 8;
    //发送群消息
    Byte GROUP_MESSAGE = 9;
    //接收群消息
    Byte GROUP_MESSAGE_RESPONSE = 10;
    //心跳消息请求
    Byte HEARTBEAT_REQUEST = 11;
    //心跳消息恢复
    Byte HEARTBEAT_RESPONSE = 12;
    //红包发起
    Byte RED_PACK_CREATED = 13;
    //加入群
    Byte JOIN_GROUP = 14;

    //私聊消息
    Byte SINGLE_MESSAGE_ACK = 15;
    //私聊响应
    Byte SINGLE_MESSAGE_ACK_OTHERCLIENT = 17;

    //私聊响应
    Byte SINGLE_MESSAGE_ACK_RESPONSE = 16;

    //私聊响应
    Byte SINGLE_MESSAGE_ACK_FIRST = 18;


    Byte HTTP_REQUEST = 16;


}
