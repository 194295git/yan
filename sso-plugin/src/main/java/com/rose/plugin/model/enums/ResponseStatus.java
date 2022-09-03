package com.rose.plugin.model.enums;

/**
 * 响应结果的枚举
 *
 * @author fengshuonan
 * @date 2018-02-04 12:01
 */
public enum ResponseStatus {

    SUCCESS(200, "成功"),
    WRONG_TOKEN(600, "token错误");

    Integer code;
    String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
