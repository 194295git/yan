package com.rose.yaj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rosejava
 * @since 2020-10-04
 */
public interface YanGroupMapper extends BaseMapper<YanGroup> {

    boolean addMember(@Param("groupId") String groupId, @Param("memberIds") List<String> memberIds);

    List<String> getGroupOpenid(@Param("id") Integer id);
}
