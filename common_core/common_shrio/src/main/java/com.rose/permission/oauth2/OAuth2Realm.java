/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.rose.permission.oauth2;


import com.google.gson.Gson;

import com.rose.common.constant.RedisPrefix;
import com.rose.permission.entity.SysUserPermission;
import com.rose.permission.util.JwtTokenUtil;
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
        SysUserPermission user = (SysUserPermission)principals.getPrimaryPrincipal();
        Set<String> permsSet = user.getPermissions();
        //用户权限列表
        //将这块改成从redis里面获得，是不是就轻松多了。然后需要做的是存储。在登录的时候存储进去。
        // 然后就可以完成鉴权了

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }



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
            String openid = claims.get("openid").toString();
            Gson gson = new Gson();
//            Set<String> permsSet = shiroService.getUserPermissions(userId);
//            SysUserPermission user = shiroService.queryUser(userId);
            String  permsSetString = ( String )redisTemplate.opsForValue().get(RedisPrefix.SHIROPERFIX +openid);
            Set<String> permsSet=  gson.fromJson(permsSetString,Set.class);
            SysUserPermission user = new SysUserPermission();
            user.setUserId(userId);
            user.setPermissions(permsSet);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            /**
             * 应该抛出异常 而不是return return有点问题
             */
            throw new IncorrectCredentialsException("token失效，请重新登录");
//            return null;
        }
    }
}
