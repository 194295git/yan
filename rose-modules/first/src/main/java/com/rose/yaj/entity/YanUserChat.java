package com.rose.yaj.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("yan_im_user_chat")
public class YanUserChat {
    @TableId
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

    /**
     * 后续会改成status来完成状消息的已读等.
     */
    @TableField("msg_id")
    private String msgId;

    //新加群聊功能
    @TableField("`group`")
    private Boolean group;



    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
