package com.netty.informationServe.serve.handler;

/**
 * @author rose
 * @create 2023/6/27
 */


import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.URLUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * URL参数处理程序，这时候连接还是个http请求，没有升级成webSocket协议，此处SimpleChannelInboundHandler泛型使用FullHttpRequest
 *
 * @author Nanase Takeshi
 * @date 2022/5/7 15:07
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyWebSocketParamHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    /**
     * 此处进行url参数提取，重定向URL，访问webSocket的url不支持带参数的，带参数会抛异常，这里先提取参数，将参数放入通道中传递下去，重新设置一个不带参数的url
     *
     * @param ctx     the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *                belongs to
     * @param request the message to handle
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String uri = request.uri();
        log.info("NettyWebSocketParamHandler.channelRead0 --> : 格式化URL... {}", uri);
        Map<CharSequence, CharSequence> queryMap = UrlBuilder.ofHttp(uri).getQuery().getQueryMap();
        //将参数放入通道中传递下去
        AttributeKey<String> attributeKey = AttributeKey.valueOf("token");
        ctx.channel().attr(attributeKey).setIfAbsent(queryMap.get("token").toString());
        request.setUri(URLUtil.getPath(uri));
        ctx.fireChannelRead(request.retain());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.error("NettyWebSocketParamHandler.exceptionCaught --> cause: ", cause);
        ctx.close();
    }

}
