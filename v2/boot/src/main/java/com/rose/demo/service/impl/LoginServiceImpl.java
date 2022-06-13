package com.rose.demo.service.impl;

import com.rose.demo.common.GenericResponse;
import com.rose.demo.common.ServiceError;
import com.rose.demo.dao.LoginDao;
import com.rose.demo.entity.User;
import com.rose.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author rose
 * @create 2022/5/18
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    @Override
    public GenericResponse regist(String email, String password,String username) {
        // 1. 检测注册之前有没有已经注册过了
        User rs = loginDao.selectByEmail(email);
        if(rs !=null){
            return GenericResponse.response(ServiceError.EMAIL_USEED);
        }

        User user = new User();
        String newOpenid = uuid();
        user.setUsername(username);
        user.setOpenid(newOpenid);
        user.setPassword(password);
        user.setEmail(email);
        // 2.向数据库中插入数据
        int rs2 =loginDao.register(user);
        if (rs2>0){
            return GenericResponse.response(ServiceError.NORMAL);
        }else {
            return GenericResponse.response(ServiceError.DB_INSERT_ERROR);
        }

    }
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
