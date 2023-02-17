package com.netty.informationServe.serve.handler;

import com.netty.common.domain.User;
import com.netty.informationServe.protocol.packet.RegisterPacket;
import com.netty.informationServe.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @创建人 rose
 * @创建时间 2021/12/2
 * @描述 注册处理器
 */
@Service
@ChannelHandler.Sharable
public class RegisterHandler extends SimpleChannelInboundHandler<RegisterPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RegisterPacket registerPacket) throws Exception {
        User loginUser = registerPacket.getUser();
        SessionUtils.bindChannel(loginUser, channelHandlerContext.channel());
        if (SessionUtils.hasLogin(channelHandlerContext.channel())) {
            System.out.println("RegisterHandler"+loginUser.getUserName()+"该用户已登录");
        }
    }
}
