package com.netty.informationServe.serve.handler;

import com.netty.common.domain.User;
import com.netty.informationServe.protocol.packet.RegisterPacket;
import com.netty.informationServe.service.ChannelService;
import com.netty.informationServe.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 注册处理器
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class RegisterHandler extends SimpleChannelInboundHandler<RegisterPacket> {

    @Autowired
    ChannelService channelService;


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RegisterPacket registerPacket) throws Exception {
        User loginUser = registerPacket.getUser();
        //如果登录状态是1先绑定用户
        if(("1").equals(loginUser.getLoginStatus())){
            SessionUtils.bindChannel(loginUser, channelHandlerContext.channel());
            //这个地方绑定一下客户端与服务器的关系；
            channelService.put(loginUser.getOpenid(),channelHandlerContext.channel());

            if (SessionUtils.hasLogin(channelHandlerContext.channel())) {
                log.info("RegisterHandler=======>"+loginUser.getUserName()+"该用户已登录");
            }
        }
        //如果是0清楚缓存
        if(("0").equals(loginUser.getLoginStatus())){
            SessionUtils.clearChannel(loginUser, channelHandlerContext.channel());

        }

    }
}
