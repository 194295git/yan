package com.rose.file.controller.bilibiliApi;/**
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
        ProjectRequest p = new ProjectRequest(1715923122092L, "iZUV0rJXkl0F5YhSD0kfvt9w", "gT9inm1Qw04Kl9MUYElCsZtjiSktsO");
        //获取弹幕服务信息
        String result = p.start("DIBITDO9ESCG0");
        JSONObject data = JSONObject.parseObject(result).getJSONObject("data");
        //个人信息
        JSONObject anchorInfo = data.getJSONObject("anchor_info");
        //弹幕服务器信息
        JSONObject websocketInfo = data.getJSONObject("websocket_info");
        //弹幕服务器地址
        JSONArray wssLinks = websocketInfo.getJSONArray("wss_link");
        //websocket鉴权信息
        String authBody = websocketInfo.getString("auth_body");
        //选一个服务器节点
        String uri = wssLinks.getString(0);
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        // 连接到WebSocket服务器
        container.connectToServer(new WebsocketListener(authBody), new URI(uri));
    }

}
