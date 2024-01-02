package com.netty.informationServe.serve.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netty.common.domain.User;
import com.netty.informationServe.config.NettyConfig;
import com.netty.informationServe.protocol.Packet;
import com.netty.informationServe.protocol.Commond;
import com.netty.informationServe.protocol.packet.*;
import com.netty.informationServe.service.ChannelService;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.base.WebsocketMessage;
import com.rose.common.constant.NettyConstants;
import com.rose.common.mqutil.SendRequest;
import com.rose.common.netty.AttrConstants;
import com.rose.common.utils.UUIDUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @创建人 rose
 * @创建时间 2021/11/22
 * @描述 接收/处理/响应客户端websocket请求的业务核心处理类  模板设计，子类重写某些类  SimpleChannelInboundHandler是一个模板类
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class MyWebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ChannelService channelService;

//    服务端处理客户端websocket请求的核心方法
//    这是模板方法的实现
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame msg) throws Exception {

            handWebsocketFrame(channelHandlerContext, msg);

    }

    /**

     *@描述 处理客户端与服务端之间的业务

     */
    private void handWebsocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
        if(frame instanceof CloseWebSocketFrame) {
            ctx.channel().close();
        }

//        判断是否时ping消息
        if(frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
        }

////        判断是否是二进制消息，如果是二进制消息，就抛出异常
//        if(!(frame instanceof BinaryWebSocketFrame)) {
//            System.out.println("目前我们不支持二进制消息");
//            throw new RuntimeException(this.getClass().getName() + ":不支持消息");
//        }

        TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) frame;
        ByteBuf bytebuf = textWebSocketFrame.content();
//从content中写入缓冲区
        String content = bytebuf.toString(Charset.forName("utf-8"));
        logger.info(content);
        JSONObject jsonObject = JSONObject.parseObject(content);
//将json字符串转变为json对象
        logger.info(content);
//从json对象中按属性取值
        Byte type = jsonObject.getByte("type");
        JSONObject parmas = jsonObject.getJSONObject("params");
        Packet packet = null;

        switch (type) {
            // 注册user-->channel 映射
            case 7:
                RegisterPacket registerRequestPacket = new RegisterPacket();
//                将json对象转换为实体类
                User user =  JSON.parseObject(parmas.toJSONString(), User.class);
                registerRequestPacket.setUser(user);
                packet = registerRequestPacket;
                break;
            // 单聊
            case 1:
                SingleMessagePacket messageRequestPacket = new SingleMessagePacket();
                messageRequestPacket.setMessage(parmas.getString("message"));
                messageRequestPacket.setToUserId(parmas.getString("toMessageId"));
                messageRequestPacket.setFileType(parmas.getString("fileType"));
                packet = messageRequestPacket;
                break;
            // 单聊ack
            case 15:
                AckSingleMessagePacket ackmessageRequestPacket = new AckSingleMessagePacket();
                WebsocketMessage websocketMessage= new  WebsocketMessage();
                websocketMessage.setFrom("client");
                websocketMessage.setMessageId(parmas.getString("msgid"));
                websocketMessage.setFrom(parmas.getString("fromUser"));
                String[] touser = {parmas.getString("toUser")};
                websocketMessage.setTo(touser);
                ackmessageRequestPacket.setWebsocketMessage(websocketMessage);
                packet = ackmessageRequestPacket;
                break;
            // 创建群聊
            case 3:
                CreateGroupPacket createGroupPacket = new CreateGroupPacket();
                String userListStr = parmas.getString("userIdList");
                log.info(userListStr);
                List<String> userIdList = Arrays.asList(userListStr.split(","));
                log.info(userIdList.toString());
                createGroupPacket.setUserIdList(userIdList);
                createGroupPacket.setGroupId(parmas.getInteger("groupId"));
                packet = createGroupPacket;
                break;
            // 群聊消息
            case 9:
                GroupMessagePacket groupMessageRequestPacket = new GroupMessagePacket();
                groupMessageRequestPacket.setMessage(parmas.getString("message"));
                groupMessageRequestPacket.setToGroupId(parmas.getInteger("toMessageId"));
                groupMessageRequestPacket.setFileType(parmas.getString("fileType"));
                packet = groupMessageRequestPacket;
                break;
            //心跳检测 暂未添加
            default:
                break;
        }

        //        返回应答消息
//        获取客户端向服务端发送的消息
        if(type.equals(Commond.SINGLE_MESSAGE)||type.equals(Commond.GROUP_MESSAGE)){
            ByteBuf buf = getByteBuf(ctx, parmas.getString("message"));
            TextWebSocketFrame tws = new TextWebSocketFrame(buf);
            ctx.writeAndFlush(tws);
        }
        //返回给client b的回应
        if(type.equals(Commond.SINGLE_MESSAGE_ACK)){
            ByteBuf buf = getByteBufAck(ctx, parmas.getString("msgid"));
            TextWebSocketFrame tws = new TextWebSocketFrame(buf);
            ctx.writeAndFlush(tws);
        }

//        log.info(packet.getCommand());

        ctx.fireChannelRead(packet);
    }

    //客户端与服务端创建连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
       log.info("channelActive客户端与服务端连接开启....");
    }

    //客户端与服务端断开连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //删除redis里面的数据
        if( SessionUtils.getUser(ctx.channel()) == null){
            log.info("当前channel没有登录用户");
            return;

        }

        channelService.remove(SessionUtils.getUser(ctx.channel()).getOpenid());
        //删除缓存的hashmap
        NettyConfig.group.remove(ctx.channel());
       log.info("channelInactive客户端与服务端连接关闭....");
    }

    //接收结束之后 read相对于服务端
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

//    出现异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx, String message) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        User fromUser = SessionUtils.getUser(ctx.channel());
        JSONObject data = new JSONObject();
        data.put("type", Commond.SELF_RESPONSE);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("date", new Date().toString());
        data.put("params", params);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public ByteBuf getByteBufAck(ChannelHandlerContext ctx, String message) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        User fromUser = SessionUtils.getUser(ctx.channel());
        JSONObject data = new JSONObject();
        data.put("type", Commond.SINGLE_MESSAGE_ACK_RESPONSE);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("date", new Date().toString());
        data.put("params", params);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }


    //构造推送消息体
    private WebsocketMessage getMessage(String channelId, SendRequest request, MessageExt msg) {
        Channel channel =SessionUtils.getChannel(channelId);
        WebsocketMessage websocketMsg = new WebsocketMessage(
                request.getRequestId(),
                channel.attr(AttrConstants.sessionId).get(),
                UUIDUtils.getUUID(),
                WebsocketMessage.MsgType.BUSSINESS.code,
                new String[]{channelId},
                request.getMsg(),
                request.getFrom(),
                Integer.parseInt(msg.getUserProperty(NettyConstants.Trigger))
        );
        return websocketMsg;
    }

}
