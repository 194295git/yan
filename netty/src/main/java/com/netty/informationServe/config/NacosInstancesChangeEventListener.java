package com.netty.informationServe.config;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.event.InstancesChangeEvent;
import com.alibaba.nacos.common.notify.NotifyCenter;
import com.alibaba.nacos.common.notify.listener.Subscriber;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.rose.common.constant.RedisPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author rose
 * @create 2023/10/28
 */
@Component
@Slf4j
public  class NacosInstancesChangeEventListener extends Subscriber<InstancesChangeEvent> {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void registerToNotifyCenter(){
        NotifyCenter.registerSubscriber(this);
    }

    /**
     * 这个地方的功能需要添加进入redis缓存.
     * @param event
     */
    @Override
    public void onEvent(InstancesChangeEvent event) {
        log.info("监听nacos的服务实例变化情况:{}", JacksonUtils.toJson(event));
        Instance instanceInfo = event.getHosts().get(0);
//        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info("服务注册事件:"+event.getServiceName().toLowerCase()+"-"+instanceInfo.getIp()+":"+instanceInfo.getPort());
//        if(NettyConstants.WEBSOCKET_SERVER.equalsIgnoreCase(event.getServiceName())){
            if(!redisTemplate.opsForHash().hasKey(RedisPrefix.WEBSOCKETSERVER,instanceInfo.getInstanceId())){
                redisTemplate.opsForHash().put(RedisPrefix.WEBSOCKETSERVER,instanceInfo.getInstanceId(),"");
            }
//        }
//        log.info("Spring Gateway 接收实例刷新事件：{}, 开始刷新缓存", JacksonUtils.toJson(event));
//
//        log.info("Spring Gateway 实例刷新完成");
    }

    @Override
    public Class<? extends com.alibaba.nacos.common.notify.Event> subscribeType() {
        return InstancesChangeEvent.class;
    }
}
