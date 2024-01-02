package com.netty.informationServe.serve;

import com.netty.informationServe.serve.handler.MyWebSocketChannelHandler;
import com.netty.informationServe.utils.Nettyutil;
import com.rose.common.constant.RedisPrefix;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @创建人 rose
 * @创建时间 2021/11/30
 * @描述
 */
@ChannelHandler.Sharable
@Component
@Slf4j
public class WebSocketServe {
    // 创建mainReactor
    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    // 创建工作线程组
    EventLoopGroup workGroup = new NioEventLoopGroup();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    Nettyutil nettyutil;
    private static final int InitPort = 8888;
    private static final int tryMaxCount = 3;
    private static int tryCount = 0;
    private static String nettyPort = "";

    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    //如何取到nacos里面的实例id呢;
//    @Value("${server.port}:${server.port}")
//    public String instanceId;
    @Value("${server.port}")
    public String port;

    /**
     * 启动方法里面使用到的channel
     */
    private Channel channel;

    @Autowired
    MyWebSocketChannelHandler myWebSocketChannelHandler;

//    public void run(){
//
//        try{
//            ServerBootstrap bootstrap = new ServerBootstrap();
//            bootstrap.group(bossGroup,workGroup)
//                     //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数  还有几个选项没加，需要学习
//                     .option(ChannelOption.SO_BACKLOG, 1024)
//                     .channel(NioServerSocketChannel.class)
//                    //添加日志处理器？或者切面
//                     .childHandler(myWebSocketChannelHandler);
//            System.out.println("WebSocketServe========>客户端等待连接....");
//            Channel ch = bootstrap.bind(8888).sync().channel();
//            ch.closeFuture().sync();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
////            优雅的退出程序
//            bossGroup.shutdownGracefully();
//            workGroup.shutdownGracefully();
//        }
//    }
    public ChannelFuture run(){
        ChannelFuture f = null;
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数  还有几个选项没加，需要学习
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .channel(NioServerSocketChannel.class)
                    //添加日志处理器？或者切面
                    .childHandler(myWebSocketChannelHandler);
            System.out.println("WebSocketServe========>客户端等待连接....");
            f = bind(bootstrap,InitPort);
            channel = f.channel();
            //尝试三次 服务初始化
            //或者改一下直接获取当前服务名称下面的所有实例来注册redis
            init();

        }catch (Exception e) {
            log.error("Netty start error:", e);
        }
        return f;
    }
    /**
     * 递归启动，从port端口开始，绑定不成功就+1 继续绑定
     * @param serverBootstrap
     * @param port
     * @return
     */
    private static ChannelFuture bind(final ServerBootstrap serverBootstrap, final int port) {
        return serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    log.info("netty端口在[" + port + "]启动成功!");
                    nettyPort = port+"";
                } else {
                    log.info("netty端口在[" + port + "]启动失败,继续尝试启动...");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }

    private void init(){
        try {
            //设置redis中记录的websocket服务地址的连接端口
            setRedisWebsocketPort();
        }catch (Exception e){
            log.error("初始化失败，程序退出...",e);
            System.exit(0);
        }
    }

    public void destroy() {
        if(channel != null) { channel.close();}
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }


    /**
     * 我这里面的逻辑不太一样，他那个地方启动nacos的时候自动push了一个；
     * 递归往redis里面放值
     */
    private void setRedisWebsocketPort() {

        String instanceid = nettyutil.getInstance();
        //这个地方的逻辑就是创建一个neety_port
        if(redisTemplate.opsForHash().hasKey(RedisPrefix.WEBSOCKETSERVER,instanceid)){
            redisTemplate.opsForHash().put(RedisPrefix.WEBSOCKETSERVER,instanceid,nettyPort);
            log.info("设置实例[{}]的netty端口为[{}].",instanceid,nettyPort);
        }else{
            log.info("setRedisWebsocketPort时 不存在[{}]的..",nettyPort);
            tryCount++;
            if(tryCount<=tryMaxCount){
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    log.error("启动失败，继续尝试",e);
                }
                setRedisWebsocketPort();
            }else{
                System.exit(0);
            }
//            throw new
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                log.error(e.getMessage());
//            }
//            setRedisWebsocketPort();
        }
    }
}
