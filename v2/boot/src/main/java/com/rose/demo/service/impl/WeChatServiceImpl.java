package com.rose.demo.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.rose.demo.common.GenericResponse;
import com.rose.demo.common.ServiceError;
import com.rose.demo.entity.User;
import com.rose.demo.service.WeChatService;
import com.rose.demo.util.JwtTokenUtil;
import com.rose.demo.wechat.Jcode2SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 微信业务实现类
 * 获取code的过程使相当于用户授权直接与微信服务器传值获取code
 * jcode2Session()这个函数就可以将code传进去，在将openid传出
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    @Value("${weChat.appid}")
    private String appid;

    @Value("${weChat.secret}")
    private String secret;


//    @Autowired
//    private RedisUtil redisUtil;

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }



    @Override
    public GenericResponse wxLogin(String code) throws Exception {
        JSONObject sessionInfo = JSONObject.parseObject(jcode2Session(code));



        User user = new User();
        user.setOpenid(sessionInfo.getString("openid"));
        user.setUsername("rose_test");

//        int count = yanUserService.count(new QueryWrapper<User>().eq("openid", sessionInfo.getString("openid")));
//        if (count>0){
//            System.out.println("用户已经访问过");
//        }else {
//            boolean save = yanUserService.save(user);
//            if (save){
//                System.out.println("保存成功");
//            }else {
//                System.out.println("保存失败");
//            }
//
//        }

        String token = JwtTokenUtil.generateToken(user);
        //这一步，关键的代码，先生成token，在将token转到redis里面去管理；
//        redisUtil.hset(token,hashMap);

//        return GenericResponse.response(ServiceError.NORMAL, sessionInfo);
        return GenericResponse.response(ServiceError.NORMAL, token);
    }

    /**
     * 登录凭证校验
     *
     * @param code
     * @return
     * @throws Exception
     */
    private String jcode2Session(String code) throws Exception {
        String sessionInfo = Jcode2SessionUtil.jscode2session(appid, secret, code, "authorization_code");//登录grantType固定
        System.out.println(sessionInfo);
        log.info(sessionInfo);
        return sessionInfo;
    }


}
