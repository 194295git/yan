package com.rose.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author rose
 * @create 2023/7/16
 */
@Data
public class MQProducerTemp {
    private Integer id;
    private String  topic;
    private String  tags;
    private String  keys;
    private String  body;
    private String  exception;
    private Date createTime;

}
