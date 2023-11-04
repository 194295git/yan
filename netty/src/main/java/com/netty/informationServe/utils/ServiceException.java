package com.netty.informationServe.utils;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException{

    private Integer code;

    private String message;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
