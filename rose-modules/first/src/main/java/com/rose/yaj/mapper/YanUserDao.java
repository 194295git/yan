package com.rose.yaj.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author rose
 * @create 2021/8/4
 */
public interface YanUserDao {
    List<Map<String,Object>> insertSelective();
}
