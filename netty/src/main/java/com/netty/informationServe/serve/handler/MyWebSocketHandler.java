package com.netty.informationServe.serve.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netty.common.domain.User;
import com.netty.informationServe.config.NettyConfig;
import com.netty.informationServe.config.RoseFeignConfig;
import com.netty.informationServe.feign.NettyMqFeign;
import com.netty.informationServe.protocol.Packet;
import com.netty.informationServe.protocol.packet.*;
import com.netty.informationServe.service.AuthService;
import com.netty.informationServe.service.ChannelService;
import com.netty.informationServe.utils.SessionUtils;
import com.rose.common.base.WebsocketMessage;
import com.rose.common.netty.Commond;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    AuthService authService;

    @Autowired
    private NettyMqFeign nettyMqFeign; // Feign客户端
    /**
     * 事件回调  在这个地方完成参数认证和授权.需要去调用一个接口去测试.
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //检查用户openid
        AttributeKey<String> openid = AttributeKey.valueOf("openid");
        //从通道中获取用户openid
        String openid2 = ctx.channel().attr(openid).get();
        log.info("MyWebSocketHandler.userEventTriggered=>"+openid2);


        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            //协议握手成功完成
            log.info("MyWebSocketHandler.userEventTriggered --> : 协议握手成功完成");
            //检查用户token
            AttributeKey<String> attributeKey = AttributeKey.valueOf("token");
            //从通道中获取用户token
            String token = ctx.channel().attr(attributeKey).get();

            ctx.channel().config().setAutoRead(false);
            RoseFeignConfig.token.set(token);

//            GenericResponse rs = nettyMqFeign.getAuth();
            CompletableFuture<Boolean> authenticateFuture = authService.authenticateAsync(token);

            authenticateFuture.thenAccept(authResult -> {
                log.info("token校验通过"+authResult);
                if (authResult) {
                    log.info("token校验通过");
                } else {
                    ctx.writeAndFlush(new CloseWebSocketFrame(400, "token 无效")).addListener(ChannelFutureListener.CLOSE);
                }
                ctx.channel().config().setAutoRead(true);

            });
        }
//        //通过判断IdleStateEvent的状态来实现自己的读空闲，写空闲，读写空闲处理逻辑
        if(evt instanceof IdleStateEvent) {
            //将  evt 向下转型 IdleStateEvent
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;
            switch (event.state()) {
                case READER_IDLE:

                    closeHeart(ctx, openid2);


                    //关闭通道，并且清除所有的redis信息
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            //这里已经可以知道浏览器所处的空闲是何种空闲，可以执行对应的处理逻辑了

        }

    }
    private void closeHeart(ChannelHandlerContext ctx, String openid2) {
        String eventType = "读空闲";

        // 使用 CompletableFuture 异步执行清理逻辑
        CompletableFuture<Void> cleanupFuture = CompletableFuture.runAsync(() -> {
            channelService.remove(openid2);
            log.info(ctx.channel().remoteAddress() + "--超时时间--" + eventType);
        });

        // 在清理逻辑完成后，异步关闭 Channel
        cleanupFuture.whenComplete((__, ___) -> {
            // 触发一个空的写操作，并在完成后关闭 Channel
            ctx.writeAndFlush(new CloseWebSocketFrame(401, "心跳结束")).addListener(ChannelFutureListener.CLOSE);
           
        });
    }

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

        //      判断是否是二进制消息，如果是二进制消息，就抛出异常
//        if(!(frame instanceof BinaryWebSocketFrame)) {
//            System.out.println("目前我们不支持二进制消息");
//            throw new RuntimeException(this.getClass().getName() + ":不支持消息");
//        }

        TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) frame;
        ByteBuf bytebuf = textWebSocketFrame.content();
        String content = bytebuf.toString(Charset.forName("utf-8"));
        JSONObject jsonObject = JSONObject.parseObject(content);
        Byte type = jsonObject.getByte("type");
        JSONObject parmas = jsonObject.getJSONObject("params");
        Packet packet = null;
        Boolean toUserChannel = null;
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
                messageRequestPacket.setMsgid(parmas.getString("msgid"));
                messageRequestPacket.setIsretry(parmas.getString("isretry"));
                messageRequestPacket.settType(parmas.getString("fileType"));
                toUserChannel= sessionUtils.isOnline(messageRequestPacket.getToUserId());

                packet = messageRequestPacket;
                break;
            // 单聊ack
            case 15:
                PuahSingleMessagePacket ackmessageRequestPacket = new PuahSingleMessagePacket();
                WebsocketMessage websocketMessage= new  WebsocketMessage();
                websocketMessage.setFrom("client");
                websocketMessage.setMessageId(parmas.getString("msgid"));
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
                groupMessageRequestPacket.setMsgid(parmas.getString("msgid"));
                groupMessageRequestPacket.setMessage(parmas.getString("message"));
                groupMessageRequestPacket.setToGroupId(parmas.getInteger("toMessageId"));
                groupMessageRequestPacket.setFileType(parmas.getString("fileType"));
                groupMessageRequestPacket.settType(parmas.getString("fileType"));
                packet = groupMessageRequestPacket;
                break;
//           这个是ping包
            case 20:
                ByteBuf buf = createPongByteBuf(ctx);
                 TextWebSocketFrame tws = new TextWebSocketFrame(buf);
                ctx.writeAndFlush(tws);
//                log.info("收到了ping");
                break;

            default:
                break;
        }

        /**
         * 单聊或者群聊ack逻辑的变更.
         */
//                返回应答消息
//        获取客户端向服务端发送的消息
        if(type.equals(Commond.SINGLE_MESSAGE)){
            if(parmas.getString("isretry").equals("false")){
                ByteBuf buf = getByteBuf(ctx,
                        parmas.getString("message"),
                        parmas.getString("msgid"),toUserChannel,
                        parmas.getString("isretry")
                );
                TextWebSocketFrame tws = new TextWebSocketFrame(buf);
                ctx.writeAndFlush(tws);
            }

        }
//        //返回给client b的回应
//        if(type.equals(Commond.SINGLE_MESSAGE_ACK)){
//            ByteBuf buf = getByteBufAck(ctx, parmas.getString("msgid"));
//            TextWebSocketFrame tws = new TextWebSocketFrame(buf);
//            ctx.writeAndFlush(tws);
//        }
        if(packet!= null){
            ctx.fireChannelRead(packet);
        }
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

    public ByteBuf getByteBuf(ChannelHandlerContext ctx, String message, String msgid, Boolean toUserChannel, String isretry) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        User fromUser = SessionUtils.getUser(ctx.channel());
        JSONObject data = new JSONObject();
        data.put("type", Commond.SINGLE_MESSAGE_RESPONSE);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
//        params.put("date", new Date().toString());
        params.put("msgid",msgid);
        params.put("online",toUserChannel);
        //是否重传的消息
        params.put("isretry",isretry);
        data.put("params", params);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }


    /**
     * 创建心跳回复消息.
     * @param ctx
     * @return
     */
    public ByteBuf createPongByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();

        JSONObject data = new JSONObject();
        JSONObject params = new JSONObject();
        params.put("type", "pong");
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(currentDate);
//        params.put("date", formattedDate);
        //简单点，就先注释掉日期。
        data.put("params", params);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }



}
