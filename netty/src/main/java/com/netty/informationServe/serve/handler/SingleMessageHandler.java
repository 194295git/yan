package com.netty.informationServe.serve.handler;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.netty.common.config.MQUtils;
import com.netty.common.domain.User;
import com.netty.informationServe.protocol.packet.SingleMessagePacket;
import com.netty.informationServe.service.ChannelService;
import com.netty.informationServe.service.MessageService;
import com.netty.informationServe.service.messagedispatch.MessageDispatchService;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.constant.RedisPrefix;
import com.rose.common.mqutil.MqMessage;
import com.rose.common.mqutil.Topic;
import com.rose.common.netty.Commond;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;

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
    @Autowired
    SessionUtils sessionUtils;
    /**
     * 用于获取用户连接
     */
    @Autowired
    ChannelService channelService;
    @Autowired
    private RedisTemplate redisTemplate;

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

        String message = "";
        //分布式情况下不能根据touserChannel来判断是否离线应该注释
//        Channel toUserChannel = SessionUtils.getChannel(singleMessagePacket.getToUserId());
//        //应该修改判断离线的方式
//        log.info("SingleMessageHandler"+toUserChannel);
        String channelId =singleMessagePacket.getToUserId();
        String host = redisTemplate.opsForHash().get(RedisPrefix.PREFIX_CLIENT + channelId,"host")+"";
        //如果get不到才算是离线
        if(!host.equals("null")){
//        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            message = singleMessagePacket.getMessage();
            sendMessage("SAVECHAT",channelHandlerContext,message, singleMessagePacket.getToUserId(),
                    Topic.OnLine,true,singleMessagePacket.getMsgid(),token,singleMessagePacket.gettType());
        } else {
            message = singleMessagePacket.getMessage();
            sendMessage("SAVECHAT",channelHandlerContext,message, singleMessagePacket.getToUserId(),
                    Topic.OffLine,true, singleMessagePacket.getMsgid(),token,singleMessagePacket.gettType());
            log.info("SingleMessageHandler ======>"+singleMessagePacket.getToUserId()+" 该用户不存在或者未登录");
            /**
             * 更改这块逻辑，变动为将消息投递到mq后返回ack; 将return注释      return;
             */

        }
        /**
         * 投递消息成功后返回ack消息.
         */
        Boolean isOnline = sessionUtils.isOnline(singleMessagePacket.getToUserId());
        ByteBuf buf = getByteBuf(channelHandlerContext,
                singleMessagePacket.getMessage(),
                singleMessagePacket.getMsgid(),isOnline,
                singleMessagePacket.getIsretry()
        );
        TextWebSocketFrame tws = new TextWebSocketFrame(buf);
        channelHandlerContext.writeAndFlush(tws);


        /**
         * 推送消息将改为落库成功后推送消息.
         *   //使用mq发送替代直接发送
         *         messageService.execute(
         *                 createSendRequest(channelHandlerContext, message, singleMessagePacket.getToUserId(), fileType,singleMessagePacket.getMsgid()),
         *                 Commond.SINGLE_MESSAGE
         *         );
         *         log.info("发送了消息给" + singleMessagePacket.getToUserId() + "：" + singleMessagePacket.getMessage());
         */

    }



    /**
     * 创建投递消息成功的ack消息
     * @param ctx
     * @param message
     * @param msgid
     * @param toUserChannel
     * @param isretry
     * @return
     */
    public ByteBuf getByteBuf(ChannelHandlerContext ctx, String message, String msgid, Boolean toUserChannel, String isretry) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        User fromUser = SessionUtils.getUser(ctx.channel());
        JSONObject data = new JSONObject();
        data.put("type", Commond.SINGLE_MESSAGE_ACK_RESPONSE);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("date", System.currentTimeMillis());
        params.put("msgid",msgid);
        params.put("online",toUserChannel);
        //是否重传的消息
        params.put("isretry",isretry);
        data.put("params", params);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public void sendMessage(String topic ,ChannelHandlerContext ctx, String message, String toUser, String state, Boolean type, String msgid,String token,String ttype) {
        MqMessage messageMQ = new MqMessage();
        messageMQ.setFromId(SessionUtils.getUser(ctx.channel()).getOpenid());
        messageMQ.setToId(toUser);
        messageMQ.setType(state);
        messageMQ.setInfoContent(message);
        messageMQ.setTime(new DateTime().toString());
        messageMQ.setState(type);
        messageMQ.setMsgid(msgid);
        messageMQ.setToken(token);
        messageMQ.settTpye(ttype);
        messageDispatchService.sendForSave(topic,messageMQ);
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
