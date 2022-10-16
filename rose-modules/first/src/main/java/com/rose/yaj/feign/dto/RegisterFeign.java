package com.rose.yaj.feign.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rose
 * @create 2022/10/16
 */
@Data
public class RegisterFeign implements Serializable {
    /**
     * openid
     */
    private String openid;

    private String username;


}
