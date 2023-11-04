package com.rose.common.base;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rose
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketMessage implements Serializable {

    //消息所属的请求id
    private String requestId;
    //会话id,在同一次中保持一致
    private String sessionId;
    //推送消息的id，推送和回执保持一致
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private String messageId;
    //消息的类型
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private Integer msgType;
    //目标客户端标识
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private String[] to;
    //消息内容
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private JSONObject msg;
    //来源
    @JsonIgnore
    private String from = "system";
    //触发类型 1.接口调用触发 2.websocket通信触发
    private Integer trigger;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date activeTime = new Date();
    //错误信息
    private String resultMsg;

    public WebsocketMessage(String messageId, Integer type, String[] to, JSONObject msg, String from, Integer trigger) {
        this.messageId = messageId;
        this.msgType = type;
        this.to = to;
        this.msg = msg;
        this.from = from;
        this.trigger = trigger;
    }

    public WebsocketMessage(String requestId, String sessionId, String messageId, Integer type, String[] to, JSONObject msg, String from, Integer trigger) {
        this.requestId = requestId;
        this.sessionId = sessionId;
        this.messageId = messageId;
        this.msgType = type;
        this.to = to;
        this.msg = msg;
        this.from = from;
        this.trigger = trigger;
    }

    //消息类型标识
    public enum MsgType {
        //错误代码
        ERROR(-1,"error"),
        //发送的业务类型消息
        CONNECTION(0,"connection"),
        //发送的业务类型消息
        BUSSINESS(1,"bussiness"),
        //发送的业务类型消息的回执
        BUSSINESS_ACK(2,"bussiness_ack"),
        //心跳类型
        HEARTBEAT(3,"heartbeat"),
        //心跳类型回执
        HEARTBEAT_ACK(4,"heartbeat_ack");

        public Integer code;
        public String info;

        MsgType(Integer code, String info){
            this.code = code;
            this.info = info;
        }
        public Integer getCode() {
            return code;
        }
        public String getInfo() {
            return info;
        }
    }

    public enum Trigger {
        //触发推送的方式
        //1.接口请求的方式
        HTTP(1,"HTTP"),
        //2.websocket消息触发
        WEBSOCKET(2,"WEBSOCKET");

        public Integer code;
        public String info;

        Trigger(Integer code, String info) {
            this.code = code;
            this.info = info;
        }
        public Integer getCode() {
            return code;
        }
        public String getInfo() {
            return info;
        }
    }
}
