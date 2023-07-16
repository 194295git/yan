package com.netty.informationServe.serve;

import com.netty.informationServe.serve.handler.MyWebSocketChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @创建人 rose
 * @创建时间 2021/11/30
 * @描述
 */
@ChannelHandler.Sharable
@Component
public class WebSocketServe {
    // 创建mainReactor
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    // 创建工作线程组
    EventLoopGroup workGroup = new NioEventLoopGroup();

    @Autowired
    MyWebSocketChannelHandler myWebSocketChannelHandler;

    public void run(){

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                     //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数  还有几个选项没加，需要学习
                     .option(ChannelOption.SO_BACKLOG, 1024)
                     .channel(NioServerSocketChannel.class)
                    //添加日志处理器？或者切面
                     .childHandler(myWebSocketChannelHandler);
            System.out.println("WebSocketServe========>客户端等待连接....");
            Channel ch = bootstrap.bind(8888).sync().channel();
            ch.closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            优雅的退出程序
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
