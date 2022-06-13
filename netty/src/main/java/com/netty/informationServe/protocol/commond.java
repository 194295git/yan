package com.netty.informationServe.protocol;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 协议栈中的各个指令与其对应 也可以用枚举类型
 */
public interface commond {
    Byte SELF_RESPONSE = 0;  //自己发送的消息确认
    Byte SINGLE_MESSAGE = 1;  //私聊消息
    Byte MESSAGE_RESPONSE = 2; //私聊响应
    Byte CREATE_GROUP = 3; //创建群
    Byte CREATE_GROUP_RESPONSE = 4; //创建群响应
    Byte LOGIN_REQUEST = 5; //登录请求
    Byte LOGIN_RESPONSE = 6; // 登录响应
    Byte REGISTER = 7;  //注册请求
    Byte REGISTER_RESPONSE = 8; //注册响应
    Byte GROUP_MESSAGE = 9;  //发送群消息
    Byte GROUP_MESSAGE_RESPONSE = 10; //接收群消息
    Byte HEARTBEAT_REQUEST = 11; //心跳消息请求
    Byte HEARTBEAT_RESPONSE = 12; //心跳消息恢复
    Byte RED_PACK_CREATED = 13; //红包发起
    Byte JOIN_GROUP = 14; //加入群
}
