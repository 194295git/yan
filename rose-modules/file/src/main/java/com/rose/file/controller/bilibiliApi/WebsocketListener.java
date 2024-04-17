package com.rose.file.controller.bilibiliApi;/**
 * @author rose
 * @create 2024/4/8
 */

/**
 * @author <br>
 */

import cn.hutool.core.util.ZipUtil;

import javax.websocket.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ClientEndpoint
public class WebsocketListener {
    private Session session;
    private String authBody;

    public WebsocketListener(String authBody) {
        this.authBody = authBody;
    }
    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("已连接服务...");
        this.session = session;
        RemoteEndpoint.Async remote = session.getAsyncRemote();
        //鉴权协议包
        ByteBuffer authPack = ByteBuffer.wrap(generateAuthPack(authBody));
        remote.sendBinary(authPack);
        //每30秒发送心跳包
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            try {
                ByteBuffer heartBeatPack = ByteBuffer.wrap(generateHeartBeatPack());
                remote.sendBinary(heartBeatPack);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 0, 30, TimeUnit.SECONDS);

    }

    @OnMessage
    public void onMessage(ByteBuffer byteBuffer) {
        //解包
        unpack(byteBuffer);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("关闭Websocket服务: " + closeReason);
    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println("Websocket服务异常: " + t.getMessage());
    }

    public interface Opt{
        short HEARTBEAT = 2;//	客户端发送的心跳包(30秒发送一次)
        short HEARTBEAT_REPLY = 3;//	服务器收到心跳包的回复 人气值，数据不是JSON，是4字节整数
        short SEND_SMS_REPLY = 5;//	服务器推送的弹幕消息包
        short AUTH = 7;//客户端发送的鉴权包(客户端发送的第一个包)
        short AUTH_REPLY = 8;//服务器收到鉴权包后的回复
    }
    public interface Version{
        short NORMAL = 0;//Body实际发送的数据——普通JSON数据
        short ZIP = 2; //Body中是经过压缩后的数据，请使用zlib解压，然后按照Proto协议去解析。
    }

    /**
     * 封包
     * @param jsonStr 数据
     * @param code 协议包类型
     * @return
     * @throws IOException
     */
    public static byte[] pack(String jsonStr, short code) throws IOException {
        byte[] contentBytes = new byte[0];
        if(Opt.AUTH == code){
            contentBytes = jsonStr.getBytes();
        }
        try(ByteArrayOutputStream data = new ByteArrayOutputStream();
            DataOutputStream stream = new DataOutputStream(data)){
            stream.writeInt(contentBytes.length + 16);//封包总大小
            stream.writeShort(16);//头部长度 header的长度，固定为16
            stream.writeShort(Version.NORMAL);
            stream.writeInt(code);//操作码（封包类型）
            stream.writeInt(1);//保留字段，可以忽略。
            if(Opt.AUTH == code){
                stream.writeBytes(jsonStr);
            }
            return data.toByteArray();
        }
    }


    /**
     * 生成认证包
     * @return
     */
    public static byte[] generateAuthPack(String jsonStr) throws IOException {
        return pack(jsonStr, Opt.AUTH);
    }
    /**
     * 生成心跳包
     * @return
     */
    public static byte[] generateHeartBeatPack() throws IOException {
        return pack(null, Opt.HEARTBEAT);
    }


    /**
     * 解包
     * @param byteBuffer
     * @return
     */
    public static void unpack(ByteBuffer byteBuffer){
        int packageLen = byteBuffer.getInt();
        short headLength = byteBuffer.getShort();
        short protVer = byteBuffer.getShort();
        int optCode = byteBuffer.getInt();
        int sequence = byteBuffer.getInt();
        if(Opt.HEARTBEAT_REPLY == optCode){
            System.out.println("这是服务器心跳回复");
        }
        byte[] contentBytes = new byte[packageLen - headLength];
        byteBuffer.get(contentBytes);
        //如果是zip包就进行解包
        if(Version.ZIP == protVer){
            unpack(ByteBuffer.wrap(ZipUtil.unZlib(contentBytes)));
            return;
        }

        String content = new String(contentBytes, StandardCharsets.UTF_8);
        if(Opt.AUTH_REPLY == optCode){
            //返回{"code":0}表示成功
            System.out.println("这是鉴权回复："+content);
        }
        //真正的弹幕消息
        if(Opt.SEND_SMS_REPLY == optCode){
            System.out.println("真正的弹幕消息："+content);
            // todo 自定义处理

        }
        //只存在ZIP包解压时才有的情况
        //如果byteBuffer游标 小于 byteBuffer大小，那就证明还有数据
        if(byteBuffer.position() < byteBuffer.limit()){
            unpack(byteBuffer);
        }
    }
}

