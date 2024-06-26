package com.netty.informationServe.serve.handler;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.netty.common.config.MQUtils;
import com.netty.common.domain.User;
import com.netty.informationServe.protocol.packet.GroupMessagePacket;
import com.netty.informationServe.service.MessageService;
import com.netty.informationServe.service.messagedispatch.MessageDispatchService;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.mqutil.MqMessage;
import com.rose.common.mqutil.SendRequest;
import com.rose.common.mqutil.Topic;
import com.rose.common.to.mq.Message2;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 群组消息处理器
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class GroupMessageHandler extends SimpleChannelInboundHandler<GroupMessagePacket> {

    @Autowired
    private MessageService messageService;
    @Autowired
    MQUtils mqUtils;
    @Resource(name = "MQDispatchServiceImpl")
    private MessageDispatchService messageDispatchService;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessagePacket groupMessagePacket) throws Exception {
        log.info("走入GroupMessageHandler参数"+groupMessagePacket.toString());
        Integer groupId = groupMessagePacket.getToGroupId();
        String fileType = groupMessagePacket.getFileType();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        log.info(" GroupMessageHandler channelGroup"+channelGroup);

        sendMessage(channelHandlerContext,groupMessagePacket.getMessage(),groupId.toString(),Topic.OnLine,false,groupMessagePacket.getMsgid(),
                fileType);

//        messageService.execute(
//                createSendRequest(channelHandlerContext, groupId, groupMessagePacket.getMessage(), userself, fileType, nameList),
//                Commond.GROUP_MESSAGE_RESPONSE
//        );
//        if (channelGroup != null) {
//            User user = SessionUtils.getUser(channelHandlerContext.channel());
//            ByteBuf byteBuf = getByteBuf(channelHandlerContext, groupId, groupMessagePacket.getMessage(), user, fileType, nameList);
//            channelGroup.remove(channelHandlerContext.channel());//发送方不需要自己再收到消息
//
//            channelGroup.writeAndFlush(new TextWebSocketFrame(byteBuf));
//            channelGroup.add(channelHandlerContext.channel()); //发送完消息再添加回去 ---todo 是否有更好得方式
//        }
    }
    public SendRequest createSendRequest(ChannelHandlerContext ctx, Integer groupId, String message,
                                         User fromUser, String fileType, List<String> nameList){
        JSONObject data = new JSONObject();
        data.put("type", 10);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("fileType", fileType);
        params.put("fromUser", fromUser);
        params.put("groupId", groupId);
        Collections.reverse(nameList);
        params.put("nameList", nameList);
        data.put("params", params);

        SendRequest req =  new SendRequest();
        req.setTo(nameList);
        req.setSendToAll(false);
        req.setMsg(data);
        return req;
    }
    public ByteBuf getByteBuf(ChannelHandlerContext ctx, Integer groupId, String message,
                              User fromUser, String fileType, List<String> nameList) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        JSONObject data = new JSONObject();
        data.put("type", 10);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("fileType", fileType);
        params.put("fromUser", fromUser);
        params.put("groupId", groupId);
        Collections.reverse(nameList);
        params.put("nameList", nameList);
        data.put("params", params);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    /**
     *
     * @param ctx
     * @param message
     * @param toUser
     * @param state
     * @param type false 是0  true 是1  用于区分是不是群聊
     */
    public void sendMessage(ChannelHandlerContext ctx, String message, String toUser, String state, Boolean type,String msgid,String ttype) {
        MqMessage messageMQ = new MqMessage();
        messageMQ.setFromId(SessionUtils.getUser(ctx.channel()).getOpenid());
        messageMQ.setToId(toUser);
        messageMQ.setMsgid(msgid);
        messageMQ.setType(state);
        messageMQ.settTpye(ttype);
        messageMQ.setInfoContent(message);
        messageMQ.setTime(new DateTime().toString());
        messageMQ.setState(type);
        messageDispatchService.sendForSave("SAVECHAT",messageMQ);
        Message2 message2 = new Message2();
        BeanUtils.copyProperties(messageMQ,message2);
        mqUtils.MessageSend2(message2);
//        mqUtils.MessageSend(Topic.OnLineTopic,messageMQ);
    }
}
