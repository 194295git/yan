package com.rose.demo.dto;

import lombok.Data;

/**
 * @author rose
 * @create 2022/5/18
 */
@Data
public class UserDto {
    @Data
    public static class register{

        private String email;

        private String password;

        private String username;
    }
}
