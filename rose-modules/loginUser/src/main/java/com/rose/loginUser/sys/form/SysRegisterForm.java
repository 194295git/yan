package com.rose.loginUser.sys.form;

import lombok.Data;

/**
 * @author rose
 * @create 2022/10/16
 */
@Data
public class SysRegisterForm {
    private String username;
    private String password;
    private String email;

    private String openid;
}
