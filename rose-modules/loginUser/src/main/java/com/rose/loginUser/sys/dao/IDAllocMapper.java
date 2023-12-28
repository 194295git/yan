package com.rose.loginUser.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.loginUser.sys.entity.LeafAlloc;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface IDAllocMapper extends BaseMapper<LeafAlloc> {

    @Select("SELECT `key`, max_id, step, update_time FROM leaf_alloc")
    @Results(value = {
            @Result(column = "key", property = "key"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<LeafAlloc> getAllLeafAllocs();

    @Select("SELECT `key`, max_id, step FROM leaf_alloc WHERE `key` = #{tag}")
    @Results(value = {
            @Result(column = "key", property = "key"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step")
    })
    LeafAlloc getLeafAlloc(@Param("tag") String tag);

    @Update("UPDATE leaf_alloc SET max_id = max_id + step WHERE `key` = #{tag}")
    void updateMaxId(@Param("tag") String tag);

    @Update("UPDATE leaf_alloc SET max_id = max_id + #{step} WHERE `key` = #{key}")
    void updateMaxIdByCustomStep(@Param("leafAlloc") LeafAlloc leafAlloc);

    @Select("SELECT `key` FROM leaf_alloc")
    List<String> getAllTags();
}
