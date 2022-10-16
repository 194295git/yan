package com.rose.yaj;

import com.rose.yaj.service.YanUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;

/**
 * @author rose
 * @create 2021/8/18
 */
public class TestUser2  extends DemoApplicationTests{

    @Autowired
    YanUserService yanUserService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Test
    public void test5(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("hello","world"+ UUID.randomUUID().toString());
        System.out.println(ops.get("hello"));


    }
}
