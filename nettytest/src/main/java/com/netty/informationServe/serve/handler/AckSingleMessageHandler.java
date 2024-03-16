package com.netty.informationServe.serve.handler;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.config.MQUtils;
import com.netty.common.domain.User;
import com.netty.informationServe.protocol.packet.AckSingleMessagePacket;
import com.netty.informationServe.service.MessageService;
import com.netty.informationServe.service.messagedispatch.MessageDispatchService;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.mqutil.SendRequest;
import com.rose.common.netty.Commond;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建时间 2021/12/2
 * @描述 私聊消息处理器
 * @author rose
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class AckSingleMessageHandler extends SimpleChannelInboundHandler<AckSingleMessagePacket> {
    @Autowired
    MQUtils mqUtils;


    @Resource(name = "MQDispatchServiceImpl")
    private MessageDispatchService messageDispatchService;


    @Autowired
    private MessageService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AckSingleMessagePacket singleMessagePacket) throws Exception {

        Boolean onLine;
        String message = "";
        Channel toUserChannel = SessionUtils.getChannel(singleMessagePacket.getWebsocketMessage().getFrom());
        log.info("AclSingleMessageHandler"+toUserChannel);
        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            //在线的话直接ack消息发送
            message = singleMessagePacket.getMessage();
            User toUser = SessionUtils.getUser(toUserChannel);
            String fileType = singleMessagePacket.getFileType();
            String msgid = singleMessagePacket.getWebsocketMessage().getMessageId();
            //使用mq发送替代直接发送
            messageService.execute(
                    createSendRequest(channelHandlerContext, message, toUser, fileType,msgid),
                    Commond.SINGLE_MESSAGE_ACK_RESPONSE

            );
            log.info(/*singleMessagePacket.getToUserId() + */"发送了消息给" + singleMessagePacket.getToUserId() + "：" + singleMessagePacket.getMessage());
        } else {
//            message = singleMessagePacket.getMessage();
            log.info("AclSingleMessageHandler ======> 该用户不存在或者未登录");
            log.info("AclSingleMessageHandler ack用户未登录的时候要不要记录一下日志");
            return;
        }

    }

    /**
     * 生成mq需要的消息体
     * @param ctx
     * @param message
     * @param toUser
     * @param fileType
     * @return
     */
    public SendRequest createSendRequest(ChannelHandlerContext ctx, String message, User toUser, String fileType,String msgid){
        JSONObject data = new JSONObject();
        data.put("type", Commond.SINGLE_MESSAGE_ACK_OTHERCLIENT);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("fileType", fileType);
        SendRequest req =  new SendRequest();
        List<String> toList = new ArrayList<String>();
        toList.add(toUser.getOpenid());
        req.setTo(toList);
        req.setSendToAll(false);
        req.setMsg(data);
        req.setUniqueMsgid(msgid);
        return req;
    }



}
