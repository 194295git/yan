package com.rose.yaj.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatDto {


    private String toOpenid;

    private String content;

    //email 也是作为websocket的通道
    private String email;

    //新加的group
    private Boolean group;

    private String msgId;

    private String tType;

    @Data
    public static class MqChat{
        private String openid;
        private String message;
        private String toUserId;
    }

    @Data
    public static class PageChat{
        /**
         * 头像
         */
        private String avatarUrl;
        //确定消息类型
        private String ttype;
        //确定消息是自己的还是别人的;

        private String type;
        //是否是群聊
        private String group;
        private String content;

        private String msgId;

        private Date createTime;

        private String OtherOpenid;


        private String targetId;
    }
}
