package com.rose.yaj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.common.feignDto.RegisterFeign;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.entity.YanUser;
import com.rose.yaj.service.WeChatService;
import com.rose.yaj.service.YanUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
 * 微信业务实现类
 * 获取code的过程使相当于用户授权直接与微信服务器传值获取code
 * jcode2Session()这个函数就可以将code传进去，在将openid传出
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {


    @Autowired
    private YanUserService yanUserService;


    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public void validateSave(String openid,YanUser user){
        int count = yanUserService.count(new QueryWrapper<YanUser>().eq("openid", openid));
        if (count>0){
            System.out.println("用户已经访问过");
        }else {
            boolean save = yanUserService.save(user);
            if (save){
                System.out.println("保存成功");
            }else {
                System.out.println("保存失败");
            }

        }
        return;
    }

    /**
     *  登录接口目前的逻辑很混乱
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public GenericResponse loginByWeb(String email, String password) throws Exception {


        YanUser user = yanUserService.getOne(new QueryWrapper<YanUser>().eq("email", email).eq("password", password));

        return null;

        // 检验当前是否用户是否存在


    }

    @Override
    public GenericResponse registByWeb(String email, String password) {
        // TODO 检验以前的注册过没有
        int count = yanUserService.count(new QueryWrapper<YanUser>().eq("email", email));
        if (count>0){
            return GenericResponse.response(ServiceError.EMAIL_ERROT);

        }else {
            YanUser user = new YanUser();
            //明明已经设置了oenid了 这是为什么保存不成功呢；简直离谱
            String newOpenid = uuid();

//            user.setOpenid(newOpenid);
            user.setUsername("test"+new Random().nextInt());
            user.setEmail(email);
            user.setPassword(password);
            boolean save = yanUserService.save(user);
            if (save){
                return GenericResponse.response(ServiceError.NORMAL);
            }else {
                return GenericResponse.response(ServiceError.INSERT_ERROR);
            }

        }

    }

    @Override
    public GenericResponse registByOpenid(RegisterFeign dto) {
        YanUser user = new YanUser();
        //明明已经设置了oenid了 这是为什么保存不成功呢；简直离谱
        user.setOpenid(dto.getOpenid());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        boolean save = yanUserService.save(user);
        if (save){
            return GenericResponse.response(ServiceError.NORMAL);
        }else {
            return GenericResponse.response(ServiceError.INSERT_ERROR);
        }
    }


}
