package com.netty.informationServe.controller;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.domain.User;
import com.netty.informationServe.utils.SessionUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author rose
 * @create 2022/10/10
 */
@RestController
@RequestMapping("chat/mqmessage")
public class PushMessageController {

    @RequestMapping("/msg")
    public void pushMessage(@RequestParam String msg){

        //TODO 分为两个部分 1.自动推送给所有在线的用户 2. 推送给不在线的用户 前端页面要做的就是每次进入先从后端获取一下待接收的值
        // 前端的消息以弹出框的形式显示出来 往mq里面推送的时候分开保存 ，前端页面查询的时候仅仅只查询未读的；还需要给用户分级；

        //1.调接口 查询所有不在线的用户；给他们推送消息；
        //这个地方要使用openFeign的
        //2. 推送给在线的用户
        Map<String, Channel> allOnlineChannel = SessionUtils.getAllOnlineChannel();
        for(Map.Entry<String, Channel> entry:allOnlineChannel.entrySet()){
            System.out.println(entry.getKey()+"--->"+entry.getValue());
            entry.getValue().writeAndFlush(new TextWebSocketFrame(Thread.currentThread().getName()+"服务器时间" + LocalDateTime.now() + "wdy"));
            entry.getValue().writeAndFlush(new TextWebSocketFrame(msg));
        }

    }

//    public ByteBuf getByteBuf(String message
//                             ) {
//        ByteBuf byteBuf = ctx.alloc().buffer();
//        JSONObject data = new JSONObject();
//        data.put("type", 987);
//        data.put("status", 200);
//        JSONObject params = new JSONObject();
//        params.put("message", message);
//
//        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
//        byteBuf.writeBytes(bytes);
//        return byteBuf;
//    }

}
