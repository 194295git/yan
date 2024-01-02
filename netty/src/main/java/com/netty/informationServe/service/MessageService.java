package com.netty.informationServe.service;

import com.rose.common.mqutil.SendRequest;
import com.netty.informationServe.service.messagedispatch.MessageDispatchService;
import com.netty.informationServe.utils.ServiceException;
import com.rose.common.constant.RedisPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 推送消息处理类
 * @author rose
 */
@Slf4j
@Service
public class MessageService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "MQDispatchServiceImpl")
    private MessageDispatchService messageDispatchService;

    /**
     * 返回不存在的客户端
     * @param request
     * @return
     */
    public Set execute(SendRequest request) {
        checkServer();
        //查询redis中所有的websocket服务
        Set<String> set = redisTemplate.keys(RedisPrefix.PREFIX_SERVERCLIENTS + "*");
        checkServerClients(set);
        //记录查询不存在的客户端
        Set<String> notExist = new HashSet<>();
        //<服务端地址,对应的客户端结果集>
        Map<String,List<String>> hostClientsMap = new HashMap<>(set.size());
        //发送给所有在线的人.
        if (request.getSendToAll()) {
            //根据服务下的设备标识推送
            //2.消息分发给所有的websocket实例处理
            //serverKey => serverclients_10.9.217.160:9003
            for(String serverKey : set){
                //因为更改过serverclient格式，所以需要重新的改一下取的公式
                log.info(serverKey.split("\\_")[2]);
                messageDispatchService.send(serverKey.split("\\_")[2],request);
            }
        }else{
            //根据参数中的客户端标识,找出所在的服务器，先对应的服务器发起推送
            List<String> requestClients = request.getTo();
            //批量查询
//            List<Object> pipeResult = redisTemplate.executePipelined(RedisUtils.getClientHostByClientFromRedis(requestClients));
            for (int i=0;i<requestClients.size();i++) {
                //遍历list 依次存入推送消息
                //根据channelId找到对应的客户端对象所对应websocket服务的实例名
                String channelId = requestClients.get(i);
                //废弃单个查询的方式
                String host = redisTemplate.opsForHash().get(RedisPrefix.PREFIX_CLIENT + channelId,"host")+"";
//                Object hostObj = pipeResult.get(i);
//                if (hostObj==null) {
//                    notExist.add(channelId);
//                    continue;
//                }
//                String host = hostObj.toString();
                if(hostClientsMap.containsKey(host)){
                    hostClientsMap.get(host).add(channelId);
                }else{
                    List<String> clients = new LinkedList<>();
                    clients.add(channelId);
                    hostClientsMap.put(host,clients);
                }
            }
            log.info("netty 分发mq消息 不存在的客户端是[{}]", notExist);
            for(Map.Entry<String,List<String>> entry: hostClientsMap.entrySet()){
                request.setTo(entry.getValue());
                //entry.getkey()是mq将要推送过去的主题.
                messageDispatchService.send(entry.getKey(),request);
            }
        }

        return notExist;
    }

    private void checkServerClients(Set<String> set) {
        if (CollectionUtils.isEmpty(set)) {
            throw new ServiceException("没有存在连接的websocket服务");
        }
    }

    private void checkServer() {
        if(redisTemplate.opsForHash().size(RedisPrefix.WEBSOCKETSERVER)<=0){
            throw new ServiceException("没有可用的websocket服务端");
        }
    }
}
