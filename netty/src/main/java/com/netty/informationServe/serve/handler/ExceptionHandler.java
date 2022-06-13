package com.netty.informationServe.serve.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Netty异常处理机制
 * @author holiday
 * 2020-12-02
 */
@Sharable
public class ExceptionHandler extends ChannelDuplexHandler {
	
	public static ExceptionHandler INSTANCE = new ExceptionHandler();
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

     @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	// TODO Auto-generated method stub
    	 if (cause instanceof RuntimeException) {
             logger.info("pipeline全局异常处理 Handle Business Exception Success.");

        	 ByteBuf byteBuf = ctx.alloc().buffer();
     		 JSONObject data = new JSONObject();

     		 data.put("type", 500);
    		 data.put("status", 500);

			 byteBuf.writeBytes(data.toJSONString().getBytes());

			 ctx.channel().writeAndFlush(new TextWebSocketFrame(byteBuf));
         }
    }
}

