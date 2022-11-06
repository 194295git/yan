/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.loginUser.sys.oauth2;


import com.google.gson.Gson;
import com.rose.common.utils.JwtTokenUtil;
import com.rose.loginUser.sys.entity.SysUserEntity;
import com.rose.loginUser.sys.service.ShiroService;

import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 认证
 *
 * @author Mark 1942951600@qq.com
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        Set<String> permsSet = user.getPermissions();
        //用户权限列表
        //将这块改成从redis里面获得，是不是就轻松多了。然后需要做的是存储。在登录的时候存储进去。
        // 然后就可以完成鉴权了

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        return null;
//    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        Long userId;
        try {
            Claims claims = JwtTokenUtil.parseJWT(accessToken);

            userId =  Long.valueOf(claims.get("userid").toString());
            Gson gson = new Gson();
//            Set<String> permsSet = shiroService.getUserPermissions(userId);
//            SysUserEntity user = shiroService.queryUser(userId);
            String  permsSetString = ( String )redisTemplate.opsForValue().get(userId.toString());
            Set<String> permsSet=  gson.fromJson(permsSetString,Set.class);
            SysUserEntity user = new SysUserEntity();
            user.setUserId(userId);
            user.setPermissions(permsSet);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
