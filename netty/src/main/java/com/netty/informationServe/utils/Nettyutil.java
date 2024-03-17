package com.netty.informationServe.utils;

import com.rose.common.base.WebsocketMessage;
import com.rose.common.mqutil.SendRequest;
import com.rose.common.netty.AttrConstants;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author rose
 * @create 2023/10/31
 */
@Slf4j
@Component
public class Nettyutil {
    @Value("${server.port}")
    public String port;



    public  String getInstance(){
        String instanceid = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            instanceid = localHost.getHostAddress()+":"+port;
        }catch (Exception e){
            log.info("获取当前地址失败");
        }
        return instanceid;
    }

    //构造推送消息体
    public WebsocketMessage getMessage(String channelId, SendRequest request) {
        Channel channel =SessionUtils.getChannel(channelId);
        WebsocketMessage websocketMsg = new WebsocketMessage(
                request.getRequestId(),
                channel.attr(AttrConstants.sessionId).get(),
                request.getUniqueMsgid(),
                WebsocketMessage.MsgType.BUSSINESS.code,
                new String[]{channelId},
                request.getMsg(),
                request.getFrom(),
                0
//                Integer.parseInt(msg.getUserProperty(NettyConstants.Trigger))
        );
        return websocketMsg;
    }
}
