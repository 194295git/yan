package com.rose.loginUser.sys.controller;

import com.rose.common.base.GenericResponse;
import com.rose.common.base.ServiceError;
import com.rose.common.constant.RedisPrefix;
import com.rose.loginUser.sys.serviceleaf.SegmentService;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LeafController {
    private Logger logger = LoggerFactory.getLogger(LeafController.class);

    @Autowired
    private SegmentService segmentService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping(value = "/api/segment/get/{key}")
    public GenericResponse getSegmentId(@PathVariable("key") String key) {
        String leafno = get(key, segmentService.getId(key));
//        SetOperations<String, String> set = redisTemplate.opsForSet();
//        set.add(RedisPrefix.LEAF_PERFIX ,leafno);
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        Long add = opsForSet.add(RedisPrefix.LEAF_PERFIX, leafno);//往集合添加元素
        /**
         * 设置一个10分钟的有效期
         */
//        stringRedisTemplate.expire(RedisPrefix.LEAF_PERFIX,600, TimeUnit.SECONDS);
//        Long remove = opsForSet.remove("set_1", "C");//删除元素
//        String set_1 = opsForSet.pop("set_1");//随机弹出 set 中的一个元素

        return GenericResponse.response(ServiceError.NORMAL,leafno );
    }



    private String get(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
//            throw new NoKeyException();

        }
        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
//            throw new LeafServerException(result.toString());
        }
        return String.valueOf(result.getId());
    }
}
