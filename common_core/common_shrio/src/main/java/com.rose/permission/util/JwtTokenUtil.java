package com.rose.permission.util;


import com.rose.permission.entity.CommonUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: jamesluozhiwei
 * @description: jwt生成token
 */
public class JwtTokenUtil {

    private static final String SALT = "123456";//加密解密盐值

    /**
     * 生成token(请根据自身业务扩展)
     *
     * @param subject           （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims            自定义身份信息
     * @return
     */
    public static String generateToken(String subject, int expirationSeconds, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)//主题
                //.setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.HS512, SALT) // 不使用公钥私钥
                //.signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
    
    /**
     * 解析token,获得subject中的信息
     *
     * @param token
     * @return
     */
    public static String parseToken(String token) {
        String subject = null;
        try {
            subject = getTokenBody(token).getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = "123456789";
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)                 //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();     //设置需要解析的jwt
        return claims;
    }


    /**
     * 获取token自定义属性
     *
     * @param token
     * @return
     */
    public static Map<String, Object> getClaims(String token) {
        Map<String, Object> claims = null;
        try {
            claims = getTokenBody(token);
        } catch (Exception e) {
        }

        return claims;
    }


    /**
     * 解析token
     *
     * @param token
     * @return
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                //.setSigningKey(publicKey)
                .setSigningKey(SALT)
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 创建jwt
     */
    public static String generateToken(CommonUser user) throws Exception {

        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Map<String, Object> claims = new HashMap<>();
        claims.put("openid", user.getOpenid());
        claims.put("userid", user.getUserId());

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();

        // 生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();

        // 下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId("123456")                  // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(new Date(nowMillis))           // iat: jwt的签发时间
                .setIssuer("rose")          // issuer：jwt签发人
                .setSubject("{\"session_key\":\"qVXVkd123456b8YezQ==\"}")        // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥

        // 设置过期时间

            long expMillis = nowMillis + 6000000L;
            builder.setExpiration(new Date(expMillis));
        return builder.compact();
    }


   

}