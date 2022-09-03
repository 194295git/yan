package com.rose.plugin.constants;

/**
 * SSO客户端常量
 *
 * @author fengshuonan
 * @date 2018-02-03 20:39
 */
public interface SsoConstants {

    /**
     * 登录用户的session
     */
    String LOGIN_USER_SESSION = "loginUser";

    /**
     * 标记用户是否登录(是否在session中有记录)
     */
    String SESSION_LOGIN_FLAG = "SESSION_LOGIN_FLAG";

    /**
     * 标记跳转所带参数的名称
     */
    String REDIRECT_PARAM_NAME = "redirectUrl";

    /**
     * url编码
     */
    String URL_ENCODE_CHARSET = "UTF-8";

    /**
     * 跳转所带 token参数名称
     */
    String TOKEN_PARAM_NAME = "gunsToken";

    /**
     * sso退出地址
     */
    String LOGOUT_URL = "/logout";

    /**
     * 客户端请求校验token是否正确时,需要带上自己服务器的地址
     */
    String CLIENT_REQUEST_ADDR_PARAM_NAME = "clientAddr";

}
