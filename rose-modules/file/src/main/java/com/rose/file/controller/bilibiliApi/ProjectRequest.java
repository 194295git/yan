package com.rose.file.controller.bilibiliApi;/**
 * @author rose
 * @create 2024/4/8
 */

/**
 * @author <br>
 */
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProjectRequest {
    /**
     * 项目ID
     */
    private long appId;
    /**
     * 身份验证Key
     */
    private String accessKey;
    /**
     * 身份验证密钥
     */
    private String accessSecret;

    public ProjectRequest(long appId, String accessKey, String accessSecret) {
        this.appId = appId;
        this.accessKey = accessKey;
        this.accessSecret = accessSecret;
    }

    public final static String START_URL = "https://live-open.biliapi.com/v2/app/start";
    public final static String END_URL = "https://live-open.biliapi.com/v2/app/end";
    public final static String HEART_BEAT_URL = "https://live-open.biliapi.com/v2/app/heartbeat";
    public final static String BATCH_HEART_BEAT_URL = "https://live-open.biliapi.com/v2/app/batchHeartbeat";

    /**
     * 接口描述：开启项目第一步，平台会根据入参进行鉴权校验。鉴权通过后，返回长连信息、场次信息和主播信息。开发者拿到长连和心跳信息后，需要参照[长连说明]和[项目心跳]，与平台保持健康的
     * @param code 必填	string	[主播身份码]
     * param appId 必填	integer(13位长度的数值，注意不要用普通int，会溢出的)	项目ID
     */
    public String start(String code) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        Map<String,Object> params = new HashMap<>();
        params.put("code", code);
        params.put("app_id", appId);
        return post(START_URL, params);
    }
    /**
     * 接口描述：项目关闭时需要主动调用此接口，使用对应项目Id及项目开启时返回的game_id作为唯一标识，调用后会同步下线互动道具等内容，项目关闭后才能进行下一场次互动。
     * param appId 必填	integer(13位长度的数值，注意不要用普通int，会溢出的)	项目ID
     * param gameId 必填	场次id
     */
    public String end(String gameId) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        Map<String,Object> params = new HashMap<>();
        params.put("game_id", gameId);
        params.put("app_id", appId);
        return post(END_URL, params);
    }
    /**
     * 接口描述：项目开启后，需要持续间隔20秒调用一次该接口。平台超过60s未收到项目心跳，会自动关闭当前场次（game_id），同时将道具相关功能下线，以确保下一场次项目正常运行。
     * 接口地址：/v2/app/heartbeat
     * 方法：POST
     * param gameId 必填	场次id
     */
    public String heartbeat(String gameId) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        Map<String,Object> params = new HashMap<>();
        params.put("game_id", gameId);
        return post(HEART_BEAT_URL, params);
    }
    /**
     * 项目批量心跳
     * 接口地址：/v2/app/batchHeartbeat
     * 方法：POST
     * @param gameIds    必填	[]string	场次id
     * */
    public String batchHeartbeat( List<String> gameIds) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        Map<String,Object> params = new HashMap<>();
        params.put("game_ids", JSONArray.toJSONString(gameIds));
        return post(HEART_BEAT_URL, params);
    }


    /**
     * 自定义post请求
     * @param url
     * @param dataMap
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private String post(String url, Map<String,Object> dataMap) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String bodyStr = JSONObject.toJSONString(dataMap);
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        // 设置请求头
        setHeader(con, bodyStr);
        // 发送 POST 请求
        con.setDoOutput(true);
        try(DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(bodyStr);
            wr.flush();
        }
        // 获取响应结果
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))){
            // 返回响应结果
            return  bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static String KEY_CONTENT_MD5 = "x-bili-content-md5";
    public static String KEY_TIMESTAMP = "x-bili-timestamp";
    public static String KEY_SIGNATURE_NONCE = "x-bili-signature-nonce";

    /**
     * 设置请求头
     * @param con
     * @param bodyStr 请求体
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private void setHeader(HttpURLConnection con,String bodyStr) throws NoSuchAlgorithmException, InvalidKeyException {
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        /**----------------------------------------------------------------------------**/
        //必填：接受的返回结果的类型。目前只支持JSON类型，取值：application/json。
        con.setRequestProperty("Accept", "application/json");
        //必填：当前请求体（Request Body）的数据类型。目前只支持JSON类型，取值：application/json。
        con.setRequestProperty("Content-Type", "application/json");
        //必填：请求体的编码值，根据请求体计算所得。算法说明：将请求体内容当作字符串进行MD5编码。
        con.setRequestProperty(KEY_CONTENT_MD5, getContentMd5(bodyStr));
        //必填：unix时间戳，单位是秒。请求时间戳不能超过当前时间10分钟，否则请求会被丢弃。
        con.setRequestProperty(KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()/1000));
        //必填： 版本1.0
        con.setRequestProperty("x-bili-signature-version", "1.0");
        //必填：签名唯一随机数。用于防止网络重放攻击，建议您每一次请求都使用不同的随机数
        con.setRequestProperty(KEY_SIGNATURE_NONCE, UUID.randomUUID().toString());
        //必填：加密算法
        con.setRequestProperty("x-bili-signature-method", "HMAC-SHA256");
        //必填： accesskey id
        con.setRequestProperty("x-bili-accesskeyid", accessKey);
        //必填：请求签名（注意生成的签名是小写的）。关于请求签名的计算方法，请参见签名机制
        con.setRequestProperty("Authorization", generateSignature(con));
    }

    /**
     * MD5计算
     */
    private String getContentMd5(String content) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return byte2Hex( md5.digest(content.getBytes(StandardCharsets.UTF_8)) );
    }

    /**
     * 签名 HmacSHA256计算
     */
    public String generateSignature(HttpURLConnection con) throws NoSuchAlgorithmException, InvalidKeyException {
        StringBuilder s = new StringBuilder();
        s.append("x-bili-accesskeyid:").append(accessKey).append("\n");
        s.append("x-bili-content-md5:").append(con.getRequestProperty(KEY_CONTENT_MD5)).append("\n");
        s.append("x-bili-signature-method:").append("HMAC-SHA256").append("\n");
        s.append("x-bili-signature-nonce:").append(con.getRequestProperty(KEY_SIGNATURE_NONCE)).append("\n");
        s.append("x-bili-signature-version:").append("1.0").append("\n");
        s.append("x-bili-timestamp:").append(con.getRequestProperty(KEY_TIMESTAMP));
        byte[] headerByte = s.toString().getBytes(StandardCharsets.UTF_8);
        byte[] secretByte = accessSecret.getBytes(StandardCharsets.UTF_8);
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secretByte, "HmacSHA256"));
        byte[] bytes = mac.doFinal(headerByte);
        return byte2Hex(bytes);
    }

    /**
     * 字节数组转16进制字符串
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}

