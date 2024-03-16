package com.netty.informationServe.config;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import javax.annotation.Resource;

/**
 * @创建人 rose
 * @创建时间 2021/11/22
 * @描述 存储整个工程的全局配置
 */
@Resource
public class NettyConfig {
//    储存每个客户端接入进来的channel对象
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
