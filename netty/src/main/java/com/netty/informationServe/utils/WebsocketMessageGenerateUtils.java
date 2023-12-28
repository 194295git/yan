package com.netty.informationServe.utils;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.constants.AttrConstants;
import com.netty.common.constants.MessageConstants;
import com.rose.common.base.WebsocketMessage;
import com.rose.common.constant.WebsocketConstants;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 消息对象构建类
 */
public class WebsocketMessageGenerateUtils {

    public static WebsocketMessage generateWebsocketMessage(String channelId, JSONObject msgJson){
        return generateWebsocketMessage(channelId,null,null,msgJson);
    }

    public static WebsocketMessage generateWebsocketMessage(String channelId, String requestId, JSONObject msgJson){
        return generateWebsocketMessage(channelId,requestId,null,msgJson);
    }

    //根据mq中的内容构建发送消息
//    public static WebsocketMessage generateWebsocketMessage(MessageExt msg){
//        return generateWebsocketMessage(msg.getTags(),null,null,new String(msg.getBody()));
//    }

    //根据请求中的参数构建发送消息
    public static WebsocketMessage generateWebsocketMessage(String channelId, String requestId, String msgId, JSONObject msgJson){
        WebsocketMessage websocketMsg = new WebsocketMessage(
                msgId,
                WebsocketMessage.MsgType.BUSSINESS.code,
                new String[]{channelId},
                msgJson,
                WebsocketConstants.SYSTEM,
                WebsocketMessage.Trigger.HTTP.code
        );
        return websocketMsg;
    }

    //根据请求中的参数构建发送消息
    public static WebsocketMessage generateErrorWebsocketMessage(Channel channel, String format, Object... args){
        WebsocketMessage websocketMsg = new WebsocketMessage();
        websocketMsg.setResultMsg(String.format(format,args));
        websocketMsg.setSessionId(channel.attr(AttrConstants.sessionId).get());
        websocketMsg.setFrom(WebsocketConstants.SYSTEM);
        websocketMsg.setTrigger(WebsocketMessage.Trigger.WEBSOCKET.code);
        websocketMsg.setMsgType(WebsocketMessage.MsgType.ERROR.code);
        return websocketMsg;
    }

    public static WebsocketMessage generateShakeHands(Channel channel){
        WebsocketMessage websocketMsg = new WebsocketMessage();
        websocketMsg.setResultMsg(String.format(MessageConstants.ShakeSuccess,channel.attr(AttrConstants.channelId).get()));
        websocketMsg.setSessionId(channel.attr(AttrConstants.sessionId).get());
        websocketMsg.setFrom(WebsocketConstants.SYSTEM);
        websocketMsg.setMsgType(WebsocketMessage.MsgType.CONNECTION.code);
        websocketMsg.setTrigger(WebsocketMessage.Trigger.WEBSOCKET.code);
        return websocketMsg;
    }

    public static TextWebSocketFrame generateResponse(WebsocketMessage ws){
        return new TextWebSocketFrame(JSONObject.toJSONString(ws));
    }
}
