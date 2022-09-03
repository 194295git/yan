package com.rose.plugin.interceptor;


import com.rose.plugin.constants.SsoConstants;
import com.rose.plugin.properties.SsoProperties;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * sso客户端登录拦截器
 *
 * @author fengshuonan
 * @date 2018-02-03 20:35
 */
public class SsoServerInterceptor implements HandlerInterceptor {

    SsoProperties ssoProperties;

    public SsoServerInterceptor(SsoProperties ssoProperties) {
        this.ssoProperties = ssoProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        //获取当前用户登录标记
        Object sessionAttribute = session.getAttribute(SsoConstants.SESSION_LOGIN_FLAG);

        //如果没登录,跳转到登录页面
        if (sessionAttribute == null) {
            request.setAttribute(SsoConstants.REDIRECT_PARAM_NAME, request.getParameter(SsoConstants.REDIRECT_PARAM_NAME));
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        } else {
            //当前用户已经登录,通过拦截器
            String redirectUrl = request.getParameter(SsoConstants.REDIRECT_PARAM_NAME);
            response.sendRedirect(redirectUrl + "?" + SsoConstants.TOKEN_PARAM_NAME + "=" + sessionAttribute.toString());
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
