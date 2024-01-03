package com.netty.informationServe.serve.handler;

/**
 * @author rose
 * @create 2023/6/27
 */
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 自定义WebSocket处理程序
 * SimpleChannelInboundHandler的泛型需要传递WebSocketFrame
 *
 * @author Nanase Takeshi
 * @date 2022/5/7 15:07
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyWebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    /**
     * 事件回调
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            //协议握手成功完成
            log.info("NettyWebSocketHandler.userEventTriggered --> : 协议握手成功完成");
            //检查用户token
            AttributeKey<String> attributeKey = AttributeKey.valueOf("token");
            //从通道中获取用户token
            String token = ctx.channel().attr(attributeKey).get();

//            ctx.fireChannelRead();
            //校验token逻辑
            //......
//            if(1 == 2) {
//                //如果token校验不通过，发送连接关闭的消息给客户端，设置自定义code和msg用来区分下服务器是因为token不对才导致关闭
//                ctx.writeAndFlush(new CloseWebSocketFrame(400, "token 无效")).addListener(ChannelFutureListener.CLOSE);
//            }
        }
        //通过判断IdleStateEvent的状态来实现自己的读空闲，写空闲，读写空闲处理逻辑
        if (evt instanceof IdleStateEvent && ((IdleStateEvent) evt).state() == IdleState.READER_IDLE) {
            //读空闲，关闭通道
            log.info("NettyWebSocketHandler.userEventTriggered --> : 读空闲，关闭通道");
            ctx.close();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame textWebSocketFrame) throws Exception {
        log.info("NettyWebSocketHandler.channelRead0 --> : 发消息来了");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("NettyWebSocketHandler.channelInactive --> : 通道关闭了");
        //获取用户关闭通道时设置的close code
        AttributeKey<Integer> attributeKey = AttributeKey.valueOf("closeCode");
        Attribute<Integer> closeCode = ctx.channel().attr(attributeKey);
        //对closeCode进行区分处理....
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("NettyWebSocketHandler.exceptionCaught --> cause: ", cause);
        ctx.close();
    }

}
