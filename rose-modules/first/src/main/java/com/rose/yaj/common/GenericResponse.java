package com.rose.yaj.common;

public class GenericResponse {

    //是否成功
    private boolean success;
    // 返回码
    private int statusCode;
    // 返回数据
    private Object content;
    //返回消息
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GenericResponse() {
    }

    public GenericResponse(boolean success, int code, String msg, Object data) {

        this.success = success;
        this.statusCode = code;
        this.msg = msg;
        this.content = data;
    }

    public static GenericResponse response(ServiceError error) {

        return GenericResponse.response(error, null);
    }

    public static GenericResponse response(ServiceError error, Object data) {

        if (error == null) {
            error = ServiceError.UN_KNOW_ERROR;
        }
        if (error.equals(ServiceError.NORMAL)) {
            return GenericResponse.response(true, error.getCode(), error.getMsg(), data);
        }
        return GenericResponse.response(false, error.getCode(), error.getMsg(), data);
    }

    public static GenericResponse response(boolean success, int code, String msg, Object data) {

        return new GenericResponse(success, code, msg, data);
    }
}
