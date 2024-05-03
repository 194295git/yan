package com.netty.informationServe.serve.handler;

import com.netty.common.config.MQUtils;
import com.netty.informationServe.protocol.packet.PuahSingleMessagePacket;
import com.netty.informationServe.service.MessageService;
import com.netty.informationServe.service.messagedispatch.MessageDispatchService;
import com.netty.informationServe.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @创建时间 2021/12/2
 * @描述 私聊消息处理器
 * @author rose
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class AckPushSingleMessageHandler extends SimpleChannelInboundHandler<PuahSingleMessagePacket> {
    @Autowired
    MQUtils mqUtils;


    @Resource(name = "MQDispatchServiceImpl")
    private MessageDispatchService messageDispatchService;


    @Autowired
    private MessageService messageService;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PuahSingleMessagePacket singleMessagePacket) throws Exception {


        String message = "";
        log.info("不用判断登录了没有，只需要一个消息id修改消息状态而已");
        String msgid = singleMessagePacket.getWebsocketMessage().getMessageId();
        messageDispatchService.sendForUpdate("UPDATECHAT",msgid);
        //需要删除
        SessionUtils.cacheMsgidAndNumber.remove(singleMessagePacket.getWebsocketMessage().getMessageId());



    }




}
