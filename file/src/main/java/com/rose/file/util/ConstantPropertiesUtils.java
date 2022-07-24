package com.rose.file.util;

/**
 * @author rose
 * @create 2021/8/26
 */
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ConstantPropertiesUtils implements InitializingBean {

    //读取配置文件内容

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;


    @Value("${aliyun.oss.file.keyid}")
    private String keyId;


    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;


    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    //定义公开静态常量
    public static String END_POIND;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POIND = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
