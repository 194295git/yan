package com.rose.yaj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.entity.YanUserChat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YanUserChatMapper  extends BaseMapper<YanUserChat> {

    int updateBatch(List<String> itemIdList);

    List<ChatDto.PageChat> listByOpenidAll(@Param("userId") String userId, @Param("minMsgId") String minMsgId);
}
