package com.rose.common.mqutil;

/**
 * @创建人 rose
 * @创建时间 2021/12/10
 * @描述 主题名
 */
public class Topic {
    /**
     * 在线消息状态
     */
    public static final String OnLine = "onLine";

    /**
     * 离线消息状态
     */
    public static final String OffLine = "offLine";

    /**
     * 在线消息Topic
     */
    public static final String OnLineTopic = "NettyInfo";

    /**
     * 离线消息Topic 后台放弃使用，没有必要
     */
    public static final String OffLineTopic = "NettyOfflineInfo";

    /**
     * 日志Topic
     */
    public static final String LogTopic = "NettyLog";

}
