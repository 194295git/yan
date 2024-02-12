package com.rose.yaj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.yaj.entity.YanUserChat;

import java.util.List;

public interface YanUserChatMapper  extends BaseMapper<YanUserChat> {

    int updateBatch(List<String> itemIdList);
}
