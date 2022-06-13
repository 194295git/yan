package com.rose.demo.common;

public enum ServiceError {

    NORMAL(200, "操作成功"),
    UN_KNOW_ERROR(-1, "未知错误"),
    EMAIL_USEED(409, "邮箱已经使用过"),
    DB_INSERT_ERROR(410,"数据库插入失败"),
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
