package com.rose.yaj.dto;/**
 * @author rose
 * @create 2024/3/12
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rose<br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatDtoNew  implements Serializable {

    private String type;
    private Integer id;
    private String userOpenid;

    private String toOpenid;

    /**
     * 消息的内容
     */
    private String content;

    /**
     * 判断用户当前状态.
     */
    private String status;

    /**
     * 后续会改成status来完成状消息的已读等.
     */
    private Integer isRead;


    private String msgId;


    private Boolean group;

    private Date createTime;

}
