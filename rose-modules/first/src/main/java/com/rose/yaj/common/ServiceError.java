package com.rose.yaj.common;

public enum ServiceError {

    NORMAL(200, "操作成功"),
    UN_KNOW_ERROR(-1, "未知错误"),
    INSERT_ERROR(-100,"代码保存错误"),
    LOGIN_ERROR(-201,"登录出错"),
    Expire_LOGIN_ERROR(-202,"登录过期,请重新登录"),

    OSS_ERROR(-300,"oss错误"),
    UPDATE_ERROR(-400,"代码更新错误"),
    GLOBAL_ERROR(-250,"执行了全局处理异常"),
    EMAIL_ERROT(-500,"该邮箱已经注册过"),

    /**
     * Global Error
     */
    GLOBAL_ERR_NO_SIGN_IN(-10001, "未登录或登录过期/Not sign in"),
    GLOBAL_ERR_NO_CODE(-10002, "code错误/error code"),
    GLOBAL_ERR_NO_AUTHORITY(-10003, "没有操作权限/No operating rights"),


    ;


    private int code;
    private String msg;

    private ServiceError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
