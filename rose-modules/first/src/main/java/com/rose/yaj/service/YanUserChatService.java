package com.rose.yaj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.entity.YanUserChat;

import java.util.List;

public interface YanUserChatService extends IService<YanUserChat> {
    /**
     * 存储消息状态.将消息存储为send
     * @param openid
     * @param chatDto
     * @param rs
     */
    void saveChat(String openid, ChatDto chatDto,Integer rs);

    int updateChatByMsgid(List<String> chat);

    /**
     * 获取除自己以外的所有成员.
     * @param openid
     * @return
     */
    List<String> getGroupMember(String openid);

    List<ChatDto.PageChat> listByOpenid(String openid,String toOpenid);


    List<ChatDto.PageChat> listByOpenidAll(String openid,String msgid);



    List<ChatDto.PageChat> listByOpenid2(String openid, String toGroup);
}
