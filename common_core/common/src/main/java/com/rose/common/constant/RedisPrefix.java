package com.rose.common.constant;

/**
 *  @author rose
 */
public interface RedisPrefix {
    //websocket的服务地址，类型-hash<ip:port,nettyPort>，key为websocket节点的实例名(ip:port)，value为websocket端口
    public static String WEBSOCKETSERVER = "yan_websocket_server";

    //websocket实例与对应连接的权重关系
    public static String WEBSOCKETWEIGHT = "websocket_weight";

    //客户端连接前缀，后缀为客户端标识-hash
    public static String PREFIX_CLIENT = "yan_client_";

    //websocket服务所连接的客户端id集合前缀-list
    public static String PREFIX_SERVERCLIENTS = "yan_serverclients_";

    //600s是10分钟?
    public static Integer intervalClientActiveTime = 600;

    /**
     * 400s
     */
    public static  Integer intervalTime= 400;

    Integer YAN_INDEX = 500;

    String INDEX_CONTET = "yan_first_indexContentJson";

    String SHIROPERFIX = "yan_shiro_";

}
