package com.netty.informationServe.task;

import com.rose.common.constant.RedisPrefix;
import com.rose.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 定时批量更新redis中的客户端活跃时间(减少实时单个修改带来的消耗)
 */
@Component
@Slf4j
public class UpdateRedisChannelActiveTimeTask{

    //待更新至redis中的客户端标识与活跃时间
    private volatile Map<String,Date> channelActiveTime = new ConcurrentHashMap<String,Date>();

    @Autowired
    private RedisTemplate redisTemplate;



    public void addChannel(String channelId){
        //重复就覆盖  保存最新的
        channelActiveTime.put(channelId,new Date());
    }

    //批量更新redis中的客户端活跃时间
    @PostConstruct
    public void refresh(){
        new Thread(() -> {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(RedisPrefix.intervalClientActiveTime);
                    //遍历所有的 待更新至redis中的客户端标识与活跃时间
                    Iterator<Map.Entry<String,Date>> it = channelActiveTime.entrySet().iterator();
                    if(!it.hasNext()){
                        continue;
                    }
                    executePipelined(it);
                } catch (Exception e) {
                    log.error("刷新redis中客户端活跃时间失败.",e);
                }
            }
        }).start();
    }

    private void executePipelined(Iterator<Map.Entry<String,Date>> it){
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.openPipeline();
                int count = 0;
                //1000条客户端活跃时间提交一次redis
                while(it.hasNext()){
                    Map.Entry<String,Date> entry = it.next();
                    //redisTemplate.opsForHash().put(RedisPrefix.PREFIX_CLIENT+channel.attr(Constants.attrChannelId).get(),"lastActiveTime" ,DateUtils.dateToDateTime(now));
                    redisConnection.hSet((RedisPrefix.PREFIX_CLIENT+entry.getKey()).getBytes(),"lastActiveTime".getBytes(), DateUtils.dateToDateTime(entry.getValue()).getBytes() );
                    it.remove();
                    count++;
                    if(count>=1000){
                        break;
                    }
                }
                return null;
            }
        });
        if(it.hasNext()){
            executePipelined(it);
        }
    }
}
