package com.rose.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rose
 * @create 2023/7/17
 */
@Data
public class MQConsumerLog  implements Serializable {

    private Integer id;
    private String  topic;
    private String  tags;
    private String  keys;
    private String  body;
    private Integer consumeTimes;

    private String  exception;
    private Integer status;
    private Integer version;
    private Date createTime;
    private Date updateTime;
}
