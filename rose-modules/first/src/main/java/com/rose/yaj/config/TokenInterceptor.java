package com.rose.yaj.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.annotation.LoginToken;
import com.rose.yaj.annotation.PassToken;
import com.rose.yaj.entity.YanUser;
import com.rose.yaj.service.YanUserService;
import com.rose.yaj.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private YanUserService yanUserService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //将token放到http请求里，用户携带token访问请求

        String token = request.getHeader("token");

        log.info("进入拦截器 TokenInterceptor====>",token);
        // 如果不是url映射的方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //如果被@passToken标注方法,则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //如果被@LoginToken注解标注，那么验证token的有效性。
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录!");
                }else {

                }

                String openid;
                try {
                    Claims claims = JwtTokenUtil.parseJWT(token);
                    openid = (String) claims.get("openid");
                    log.info(openid);
                } catch (Exception j) {
                    throw new RuntimeException("未授权的访问!");
                }
                int count = yanUserService.count(new QueryWrapper<YanUser>().eq("openid", openid));
                if (count == 0 || count < 0) {
                    throw new RuntimeException("用户不存在，请重新登录!");
                }else{


                    // 存入openid 和相关的 token 存入这两个东西就可以了，然后就可以通过token取出openid ，但是自己好像并不需要去取出

                }
                return true;
            }
        }
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
