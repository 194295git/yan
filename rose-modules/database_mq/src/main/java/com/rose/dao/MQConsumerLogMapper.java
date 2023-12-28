package com.rose.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.entity.MQConsumerLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rose
 * @create 2023/7/17
 */
@Mapper
public interface MQConsumerLogMapper  extends BaseMapper<MQConsumerLog> {
//    void insert(MQConsumerLog consumeLog);
//
//    int update(MQConsumerLog entity, LambdaUpdateWrapper<MQConsumerLog> eq);
//
//    void updateById(MQConsumerLog entity);
}
