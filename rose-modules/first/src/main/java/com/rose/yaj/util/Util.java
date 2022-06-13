package com.rose.yaj.util;

import com.rose.yaj.common.ServiceError;
import com.rose.yaj.exeptionHandler.RRException;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class Util {
    public Util() {

    }

    public static String fromRequestToOpenid(HttpServletRequest request){
        String token = request.getHeader("token");
        String openid;
        try {
            Claims claims = JwtTokenUtil.parseJWT(token);
            openid = (String) claims.get("openid");
        } catch (Exception j) {
            throw new RRException(ServiceError.Expire_LOGIN_ERROR);
        }
        return openid;

    }
    @Test
    public  void fromTokenToOpenid(){
        String openid;
        try {
            String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJzZXNzaW9uX2tleVwiOlwicVZYVmtkMTIzNDU2YjhZZXpRPT1cIn0iLCJvcGVuaWQiOiJCRUU4RTE3QTdBNzk0QTk3OTIxMURFM0M0NTkwODI0RSIsImlzcyI6InJvc2UiLCJleHAiOjE2MjcwMzEzNzQsImlhdCI6MTYyNzAyNTM3NCwianRpIjoiMTIzNDU2In0.wm1595rCyhQqT1eSxm19QDA4wT4tuv2FFlVECfdxZoQ";
            Claims claims = JwtTokenUtil.parseJWT(token);
//            Claims claims = JwtTokenUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJzZXNzaW9uX2tleVwiOlwicVZYVmtkMTIzNDU2YjhZZXpRPT1cIn0iLCJvcGVuaWQiOm51bGwsImlzcyI6InJvc2UiLCJleHAiOjE2MjcwMzA1MTksImlhdCI6MTYyNzAyNDUxOSwianRpIjoiMTIzNDU2In0.xrZ79Svvj3SAErmN0gvKaHmVm57V2tD8QZVra5Wm3As");
            openid = (String) claims.get("openid");
            System.out.println(openid);
        } catch (Exception j) {
            throw new RuntimeException("未授权的访问!");
        }

    }



    public static String getURL() throws Exception{
        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/yan/config/config.properties"));
        properties.load(bufferedReader);
        // 获取key对应的value值
        String url = properties.getProperty("url");
        return url;
    }


}
