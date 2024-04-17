package com.rose.file.controller.bilibili;/**
 * @author rose
 * @create 2024/4/8
 */

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author <br>
 */

public class App {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException, DeploymentException {
        openLiveRoom(32417294, "LIVE_BUVID=AUTO3816102812141448; i-wanna-go-back=-1; buvid_fp_plain=undefined; DedeUserID=351891220; DedeUserID__ckMd5=f01a1ca35eb450b1; buvid4=7915B686-6BC8-27DF-B5B9-7B52692B881257347-022012119-7ltoRhP9DDX%2Fr1KOWkuzdw%3D%3D; CURRENT_FNVAL=4048; nostalgia_conf=-1; enable_web_push=ENABLE; iflogin_when_web_push=1; _uuid=12D1E629-BAFD-56ED-9EDB-BFF446D2794C91618infoc; buvid3=B0C673F2-ABF2-A342-6B50-A855BC6246CC08301infoc; b_nut=1702457808; b_ut=5; header_theme_version=CLOSE; rpdid=0zbfAHzvDA|o4YmSThW|ENM|3w1RkUKn; hit-dyn-v2=1; CURRENT_QUALITY=0; home_feed_column=5; FEED_LIVE_VERSION=V_WATCHLATER_PIP_WINDOW3; browser_resolution=1745-866; fingerprint=f09e5d346dea8727b8a62b21aabb2e0a; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MTMwMTA2MjcsImlhdCI6MTcxMjc1MTM2NywicGx0IjotMX0.MfGU7boD_FYtN1b5KbvUm6eas1_F6izsMR9LUvyySA0; bili_ticket_expires=1713010567; SESSDATA=1b96c7ba%2C1728303427%2C41029%2A41CjA08fz3wyw2rDw2Q0s4iypLuZbagtFMnAIekiLtilBxDzCBsrXkl-47nzIt4x0-6yESVlRSQTFxZzVHMkJNbHh4VmlXZGRlTHR3VkFyY0VnYTYzOEpCNHFBaGs2ZnEyQWtqdWFjaXVTcGFzUjIwSW5HNUpiSk5QNk93XzkxNll4OWI2VTVsM2V3IIEC; bili_jct=a984283571bc796407cabc71aa230b67; sid=4qcx6pci; bsource=search_baidu; buvid_fp=f09e5d346dea8727b8a62b21aabb2e0a; bp_video_offset_351891220=918734044840067107; b_lsid=9DECF8FF_18EC843D36F; Hm_lvt_8a6e55dbd2870f0f5bc9194cddf32a02=1712576634,1712756961; Hm_lpvt_8a6e55dbd2870f0f5bc9194cddf32a02=1712756961; PVID=3");
    }
    public static void openLiveRoom(int roomId,String cookie) throws IOException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException, DeploymentException {
        BiliRequest biliReqest = new BiliRequest(cookie);
        JSONObject danmuInfoData = biliReqest.getDanmuInfoData(roomId);
        //登录Token
        String token = danmuInfoData.getString("token");
        //选一个服务器节点
        JSONArray hostList = danmuInfoData.getJSONArray("host_list");
        JSONObject host = hostList.getJSONObject(0);
        String wsUrl = String.format("ws://%s:%s/sub", host.getString("host"), host.getString("ws_port"));
        //创建Websocket并连接
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(new WebsocketListener(cookie, roomId, token), new URI(wsUrl)); // 连接到WebSocket服务器
    }
}
