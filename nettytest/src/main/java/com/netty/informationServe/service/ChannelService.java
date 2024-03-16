package com.netty.informationServe.service;


import com.alibaba.fastjson.JSON;
import com.netty.common.entity.Client;
import com.netty.informationServe.task.UpdateRedisChannelActiveTimeTask;
import com.netty.informationServe.utils.Nettyutil;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.constant.RedisPrefix;
import com.rose.common.netty.AttrConstants;
import com.rose.common.utils.DateUtils;
import com.rose.common.utils.ObjUtils;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 维护客户端集合的操作类
 */
@Slf4j
@Service
public class ChannelService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    Nettyutil nettyutil;
//    @Autowired
//    private ComConfig config;
    @Autowired
    private UpdateRedisChannelActiveTimeTask updateRedisChannelActiveTimeTask;





    /**
     * 客户端websocket连接上服务器
     * @param channelId
     * @param channel
     * @return
     */
    public Channel put(String channelId, Channel channel){
        try {

            String instanceid = nettyutil.getInstance();
            //缓存服务端与客户端关联信息
            redisTemplate.opsForSet().add(RedisPrefix.PREFIX_SERVERCLIENTS+instanceid,channelId);
            //给channel对象绑定客户端channelId标识
            channel.attr(AttrConstants.channelId).set(channelId);
            //给channel对象绑定客户端sessionId会话标识，一次连接保持一致的值
            channel.attr(AttrConstants.sessionId).set(instanceid+"_"+channelId+"_"+DateUtils.getCurrentDateTimeFormat());
            //更新活跃时间
            channel.attr(AttrConstants.activeTime).set(System.currentTimeMillis()+"");
            log.info("channel注册后详细信息"+ JSON.toJSONString(channel)  );

            redisTemplate.executePipelined(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    redisConnection.openPipeline();
                    //缓存客户端信息

                    redisConnection.hMSet((RedisPrefix.PREFIX_CLIENT+channelId).getBytes(), ObjUtils.ObjToByteMap(new Client(channelId,instanceid)));
                    redisTemplate.opsForHash().putAll(RedisPrefix.PREFIX_CLIENT+channelId, ObjUtils.ObjToMap(new Client(channelId,instanceid)));
                    //设置过期时间
//                    redisTemplate.expire(RedisPrefix.PREFIX_CLIENT+channelId,config.getExpireTime(),TimeUnit.SECONDS);
                    redisConnection.expire((RedisPrefix.PREFIX_CLIENT+channelId).getBytes(), TimeUnit.SECONDS.toSeconds(RedisPrefix.intervalClientActiveTime));
                    //缓存服务端与客户端关联信息

                    redisConnection.sAdd((RedisPrefix.PREFIX_SERVERCLIENTS+instanceid).getBytes(),channelId.getBytes());
                    redisTemplate.opsForSet().add(RedisPrefix.PREFIX_SERVERCLIENTS+instanceid,channelId);
                    return null;
                }
            });
            log.info("加入了客户端：[{}]",channelId);
            return channel;
        }catch (Exception e){
            e.printStackTrace();
            log.error("加入客户端失败:["+channelId+"]",e);
        }
        return null;
    }

    /**
     * 移除客户端
     * @param
     */
    public void remove(String channelId){

        String instanceid = nettyutil.getInstance();

//        if(!StringUtils.isNotEmpty(instanceid)){return;}
//        get(instanceid);
        Channel channel = SessionUtils.getChannel(channelId);
//        if(channel==null){return;}
        try {
            String dateTime = channel.attr(AttrConstants.activeTime).get();
            //断开当前连接
//            get(instanceid).close();
//            channel.closeFuture().addListener()
            //删除redis中维护的客户端信息
            redisTemplate.delete(RedisPrefix.PREFIX_CLIENT+channelId);
            //删除redis中客户端与host的关联关系
            redisTemplate.opsForSet().remove(RedisPrefix.PREFIX_SERVERCLIENTS+instanceid,channelId);
            log.info("移除了客户端[{}],上一次的活跃时间为[{}]",
                    channelId,
                    StringUtils.isNotEmpty(dateTime)? DateUtils.dateToDateTime(new Date(Long.parseLong(dateTime))):"");
        }catch (Exception e){
            log.error("移除客户端失败["+instanceid+"]",e);
        }
    }

    /**
     * 更新活跃时间
     * @param channel
     */
    public void updateActiveTime(Channel channel){
        //更新自己维护的信息
        channel.attr(AttrConstants.activeTime).set(System.currentTimeMillis()+"");
        //更新redis维护的信息
//        redisTemplate.opsForHash().put(RedisPrefix.PREFIX_CLIENT+channel.attr(AttrConstants.attrChannelId).get(),"lastActiveTime" ,DateUtils.dateToDateTime(now));
        updateRedisChannelActiveTimeTask.addChannel(channel.attr(AttrConstants.channelId).get());
    }

}
