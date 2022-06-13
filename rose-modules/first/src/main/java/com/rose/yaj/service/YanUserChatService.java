package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.dto.UserDto;
import com.rose.yaj.entity.YanAnswerComment;
import com.rose.yaj.entity.YanUserChat;

import java.util.List;

public interface YanUserChatService extends IService<YanUserChat> {
    void saveChat(String openid, ChatDto chatDto,Integer rs);


    List<ChatDto.PageChat> listByOpenid(String openid,String toOpenid);

    List<ChatDto.PageChat> listByOpenid2(String openid, String toGroup);
}
