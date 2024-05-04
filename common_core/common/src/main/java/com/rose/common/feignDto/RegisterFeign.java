package com.rose.common.feignDto;

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

    private String email;


}
