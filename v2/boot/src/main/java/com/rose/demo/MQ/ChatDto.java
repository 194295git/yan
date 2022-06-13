package com.rose.demo.MQ;

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



    @Data
    public static class MqChat{
        private String openid;
        private String message;
        private String toUserId;
    }

    @Data
    public static class PageChat{

        private String type;

        private String content;

        private Date createTime;

    }
}
