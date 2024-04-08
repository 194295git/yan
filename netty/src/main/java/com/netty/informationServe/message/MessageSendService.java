package com.netty.informationServe.message;


import com.alibaba.fastjson.JSONObject;
import com.netty.informationServe.service.ChannelService;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.base.WebsocketMessage;
import com.rose.common.utils.UUIDUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息发送类
 */
@Slf4j
@Service
public class MessageSendService {

    @Autowired
    private ChannelService channelService;

    /**
     * 具体的推送实现方法
     * @param channelId 推送的目标对象客户端标识
     * @param wsMessage 推送的具体内容
     */
    public void sendMessage(String channelId, WebsocketMessage wsMessage){
        Channel channel = SessionUtils.getChannel(channelId);
        //0.校验客户端合法性
        if(!checkClient(channel)){
            log.info("客户端不可达:[{}]",channelId);
            return ;
        }
        //1.修改本地和redis中维护的客户端的活跃时间
        channelService.updateActiveTime(channel);
        //2.发起对客户端的推送(websocket消息)
        channel.writeAndFlush(generateMessage(wsMessage));
        //3.记录推送日志
        log.info("MessageSendService 推送消息为"+generateMessage(wsMessage));
        //4.记录消息
        SessionUtils.bindMsgidAndNumber(channelId,wsMessage);

    }

    /**
     * 通过controller接口进来的消息推送给前端.
     * @param websocketMessage
     * @return
     */
    private TextWebSocketFrame generateMessage(WebsocketMessage websocketMessage){
        //设置推送的消息id
        if (StringUtils.isEmpty(websocketMessage.getMessageId())){
            websocketMessage.setMessageId(UUIDUtils.getUUID());
        }
        //终于找到了统一报文格式的地方 这个地方获取到msg，前台就可以统一了，基本一个type 一个param
//        return new TextWebSocketFrame(JSONObject.toJSONString(websocketMessage.getMsg()));
        return new TextWebSocketFrame(JSONObject.toJSONString(websocketMessage));
    }

    private boolean checkClient(Channel channel){
        if(channel==null){
            log.info("找不到该设备[{}].");
            return false;
        }
        if(!channel.isOpen()){
            log.info("设备不可达[{}].");
            return false;
        }
        return true;
    }
}
