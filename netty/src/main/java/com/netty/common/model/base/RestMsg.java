package com.netty.common.model.base;

import com.netty.common.constants.Constants;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author ruoyi
 */
public class RestMsg<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = Constants.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = Constants.FAIL;

    private int code;

    private String msg;

    private T data;

    public static <T> RestMsg<T> ok() {
        return restResult(null, SUCCESS, null);
    }

    public static <T> RestMsg<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> RestMsg<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> RestMsg<T> ok(String msg) {
        return restResult(null, SUCCESS, msg);
    }

    public static <T> RestMsg<T> fail() {
        return restResult(null, FAIL, null);
    }

    public static <T> RestMsg<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> RestMsg<T> fail(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> RestMsg<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> RestMsg<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> RestMsg<T> restResult(T data, int code, String msg) {
        RestMsg<T> restMsg = new RestMsg<>();
        restMsg.setCode(code);
        restMsg.setData(data);
        restMsg.setMsg(msg);
        return restMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
