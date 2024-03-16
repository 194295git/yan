package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.entity.YanUserChat;
import com.rose.yaj.mapper.YanUserChatMapper;
import com.rose.yaj.service.YanUserChatService;
import com.rose.yaj.service.YanUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YanUserChatServiceImpl extends ServiceImpl<YanUserChatMapper, YanUserChat> implements YanUserChatService {

    @Autowired
    YanUserService yanUserService;

    @Autowired
    YanUserChatMapper  yanUserChatMapper;
    @Override
    public void saveChat(String openid, ChatDto chatDto,Integer rs) {
        YanUserChat yanUserChat = new YanUserChat();
        //现在拷贝的时候相当于把group也给拷贝了进去
        BeanUtils.copyProperties(chatDto,yanUserChat);
        yanUserChat.setUserOpenid(openid);
        yanUserChat.setIsRead(rs);
        yanUserChat.setStatus("send");
        yanUserChat.setMsgId(chatDto.getMsgId());
        this.save(yanUserChat);
    }

    /**
     * 批量的更新消息的状态
     * @param chat
     * @return
     */
    @Override
    public int updateChatByMsgid(List<String> chat) {
        return yanUserChatMapper.updateBatch(chat);
    }

    @Override
    public List<String> getGroupMember(String openid) {
        return null;
    }

    @Override
    public List<ChatDto.PageChat> listByOpenid(String openid,String toOpenid) {
        List<ChatDto.PageChat> pageChatOne = this.listByOpenid(openid, toOpenid, 1);
        List<ChatDto.PageChat> pageChatZero = this.listByOpenid(openid, toOpenid, 0);
        pageChatOne.addAll(pageChatZero);
        pageChatOne.sort(Comparator.comparing(ChatDto.PageChat::getCreateTime));

        // 更新未读消息
        YanUserChat yanUserChat = new YanUserChat();
        yanUserChat.setIsRead(1);
        //更改这个别人给我发的消息的未读状态；
        this.update(yanUserChat,new QueryWrapper<YanUserChat>()
                .eq("to_openid",openid)
                .eq("user_openid",toOpenid));
        return pageChatOne;
    }

    @Override
    public List<ChatDto.PageChat> listByOpenidAll(String openid, String msgid) {
        return yanUserChatMapper.listByOpenidAll(openid,msgid);
    }

    @Override
    public List<ChatDto.PageChat> listByOpenid2(String openid, String toGroup) {
        List<ChatDto.PageChat> pageChatOne = this.listByOpenid(openid, toGroup, 1);
        List<ChatDto.PageChat> pageChatZero = this.listByOpenid(openid, toGroup, 2);
        pageChatOne.addAll(pageChatZero);
        pageChatOne.sort(Comparator.comparing(ChatDto.PageChat::getCreateTime));
        return pageChatOne;
    }

    public List<ChatDto.PageChat> listByOpenid(String openid,String toOpenid,Integer flag) {
        QueryWrapper<YanUserChat> wrapper = new QueryWrapper<>();
        // 自己发出去的消息
        if (flag == 1){
            wrapper.eq("user_openid",openid).eq("to_openid",toOpenid);
        }
        //别人发给我的消息
        if (flag == 0){
            wrapper.eq("user_openid",toOpenid).eq("to_openid",openid);
        }
        //群聊中别人给我发的消息
        if(flag == 2){
            wrapper.ne("user_openid",openid).eq("to_openid",toOpenid);

        }

        List<YanUserChat> list = this.list(wrapper);
        List<ChatDto.PageChat> collect = list.stream().map(
                (item) -> {
                    ChatDto.PageChat pageChat = new ChatDto.PageChat();
                    if (flag == 1) {
                        pageChat.setType("self");

                    }
                    if (flag == 0 || flag == 2) {
                        pageChat.setType("receive");
                    }
                    if(flag == 2){
                        pageChat.setOtherOpenid(item.getUserOpenid());
                        pageChat.setAvatarUrl(yanUserService.getAvatarUrlByOpenid(item.getUserOpenid()));

                    }
                    pageChat.setContent(item.getContent());
                    pageChat.setCreateTime(item.getCreateTime());
                    return pageChat;
                }
        ).collect(Collectors.toList());
        return collect;
    }


}
