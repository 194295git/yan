package com.rose.yaj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.yaj.entity.YanUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rosejava
 * @since 2020-10-01
 */
@Repository
public interface YanUserMapper extends BaseMapper<YanUser> {
    List<Map> listAvatar();
}
