package com.netty.informationServe.serve.handler;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.netty.common.config.MQUtils;
import com.rose.common.netty.Commond;
import com.rose.common.mqutil.MqMessage;
import com.netty.common.domain.User;
import com.netty.informationServe.service.messagedispatch.MessageDispatchService;
import com.rose.common.mqutil.SendRequest;
import com.netty.informationServe.protocol.packet.SingleMessagePacket;
import com.netty.informationServe.service.MessageService;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.mqutil.Topic;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 私聊消息处理器
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class SingleMessageHandler extends SimpleChannelInboundHandler<SingleMessagePacket> {
    @Autowired
    MQUtils mqUtils;
    @Resource(name = "MQDispatchServiceImpl")
    private MessageDispatchService messageDispatchService;
    @Autowired
    private MessageService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SingleMessagePacket singleMessagePacket) throws Exception {
        //检查用户token
        AttributeKey<String> attributeKey = AttributeKey.valueOf("token");
        //从通道中获取用户token
        String token = channelHandlerContext.channel().attr(attributeKey).get();

        Boolean onLine;
        String message = "";
        Channel toUserChannel = SessionUtils.getChannel(singleMessagePacket.getToUserId());
        //应该修改判断离线的方式
        log.info("SingleMessageHandler"+toUserChannel);
        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            message = singleMessagePacket.getMessage();
            sendMessage(channelHandlerContext,message, singleMessagePacket.getToUserId(),
                    Topic.OnLine,true,singleMessagePacket.getMsgid(),token);
        } else {
            message = singleMessagePacket.getMessage();
            sendMessage(channelHandlerContext,message, singleMessagePacket.getToUserId(),
                    Topic.OffLine,true, singleMessagePacket.getMsgid(),token);
            log.info("SingleMessageHandler ======> 该用户不存在或者未登录");
            return;
        }
//        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
//            message = singleMessagePacket.getMessage();
//            sendMessage(channelHandlerContext,message, singleMessagePacket.getToUserId(), Topic.OnLine,true);
//        } else {
//            message = singleMessagePacket.getMessage();
//            sendMessage(channelHandlerContext,message, singleMessagePacket.getToUserId(), Topic.OffLine,true);
//            log.info("SingleMessageHandler ======> 该用户不存在或者未登录");
//            return;
//        }
        User toUser = SessionUtils.getUser(toUserChannel);
        String fileType = singleMessagePacket.getFileType();
//
        //使用mq发送替代直接发送
        messageService.execute(
                createSendRequest(channelHandlerContext, message, toUser, fileType,singleMessagePacket.getMsgid()),
                Commond.SINGLE_MESSAGE
        );
        //这行重要的代码就先注释
//        ByteBuf buf = getByteBuf(channelHandlerContext, message, toUser, fileType);
//        toUserChannel.writeAndFlush(new TextWebSocketFrame(buf));

        log.info(/*singleMessagePacket.getToUserId() + */"发送了消息给" + singleMessagePacket.getToUserId() + "：" + singleMessagePacket.getMessage());
    }

    /**
     * 生成mq需要的消息体
     * @param ctx
     * @param message
     * @param toUser
     * @param fileType
     * @param msgid
     * @return
     */
    public SendRequest createSendRequest(ChannelHandlerContext ctx, String message, User toUser, String fileType, String msgid){
        User fromUser = SessionUtils.getUser(ctx.channel());
        JSONObject data = new JSONObject();
        data.put("type", Commond.SINGLE_MESSAGE_OTHER);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("fileType", fileType);
        params.put("fromUser", fromUser);
        params.put("toUser", toUser);
        data.put("params", params);
//        data.put("message",messageForSave);

        SendRequest req =  new SendRequest();
        List<String> toList = new ArrayList<String>();
        toList.add(toUser.getOpenid());
        req.setTo(toList);
        req.setSendToAll(false);
        req.setMsg(data);
        req.setUniqueMsgid(msgid);
        return req;
    }
    public ByteBuf getByteBuf(ChannelHandlerContext ctx, String message, User toUser, String fileType) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        User fromUser = SessionUtils.getUser(ctx.channel());
        JSONObject data = new JSONObject();
        data.put("type", 2);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("fileType", fileType);
        params.put("fromUser", fromUser);
        params.put("toUser", toUser);

        data.put("params", params);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public void sendMessage(ChannelHandlerContext ctx, String message, String toUser, String state, Boolean type, String msgid,String token) {
        MqMessage messageMQ = new MqMessage();
        messageMQ.setFromId(SessionUtils.getUser(ctx.channel()).getOpenid());
        messageMQ.setToId(toUser);
        messageMQ.setType(state);
        messageMQ.setInfoContent(message);
        messageMQ.setTime(new DateTime().toString());
        messageMQ.setState(type);
        messageMQ.setMsgid(msgid);
        messageMQ.setToken(token);
        messageDispatchService.sendForSave(messageMQ);
//        return messageMQ;

//        mqUtils.MessageSend(Topic.OnLineTopic,messageMQ);
//        log.info("是否走到了mqUtils.MessageSend2");
//        Message2 message2 = new Message2();
//        BeanUtils.copyProperties(messageMQ,message2);
//        mqUtils.MessageSend2(message2);
    }
    public void sendMessageRabbit(ChannelHandlerContext ctx, String message, String toUser, String state, Boolean type) {
        MqMessage messageMQ = new MqMessage();
        messageMQ.setFromId(SessionUtils.getUser(ctx.channel()).getOpenid());
        messageMQ.setToId(toUser);
        messageMQ.setType(state);
        messageMQ.setInfoContent(message);
        messageMQ.setTime(new DateTime().toString());
        messageMQ.setState(type);

//        mqUtils.MessageSend(Topic.OnLineTopic,messageMQ);
//        log.info("是否走到了mqUtils.MessageSend2");
//        Message2 message2 = new Message2();
//        BeanUtils.copyProperties(messageMQ,message2);
//        mqUtils.MessageSend2(message2);
    }
}
