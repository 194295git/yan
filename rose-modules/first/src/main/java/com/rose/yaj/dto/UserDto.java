package com.rose.yaj.dto;

import lombok.Data;

@Data
public class UserDto {

    @Data
    public static class Login{

        private String email;

        private String password;
    }

    @Data
    public static class LiveUser{

        private String username;

        private String email;

        private String avatarUrl;
    }

    @Data
    public static class EyeUser{
        private String openid;

        //聊天Id
        private Integer chatId;

        private String username;

        private String email;

        private String avatarUrl;

        private Integer noRead;
    }
    @Data
    public static class UserInfo{
        private String openid;
        //希望这个地方自己开的与数据库大小写转换
        private Integer chatId;

        private String username;

        private String email;

        private String avatarUrl;

        private String major;

        private String college;
    }

    @Data
    public static class AddInfo{

        private String username;

        private String password;

        private String avatarUrl;

        private String major;

        private String college;


    }
}
