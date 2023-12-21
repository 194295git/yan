package com.netty.informationServe.config;


import com.netty.common.constants.Constants;

/**
 * RocketMQ配置
 */
public class RocketMQConfig {

    /**
     * 根据具体的实例名得到对应websocket服务的MQ topic地址
     * @param instantceId
     * @return
     */
    public static String getWebsocketTopic(String instantceId){
        return  Constants.ROCKETMQ_TOPIC_PREFIX + getMqInstance(instantceId);
    }

    /**
     * 根据具体的实例名得到对应websocket服务的MQ topic地址
     * @param instantceId
     * @return
     */
    public static String getWebsocketGroup(String instantceId){
        return  Constants.ROCKETMQ_GROUP_PREFIX + getMqInstance(instantceId);
    }

    /**
     * mq群组中不允许出现实例中的":"，故替换成"-"
     * @param instanceId
     * @return
     */
    private static String getMqInstance(String instanceId){
        return instanceId.replace(":","-").replace(".","-");
    }
}
