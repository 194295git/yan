package com.rose.loginUser.server.modular.controller;


import com.rose.plugin.constants.SsoConstants;
import com.rose.plugin.service.AuthService;
import com.rose.plugin.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static com.rose.plugin.constants.SsoConstants.LOGOUT_URL;



/**
 * 登录验证控制器
 *
 * @author stylefeng
 * @Date 2018/2/3 22:23
 */
@Controller
public class AuthController  {

    private static final String LOGIN_TIPS = "tips";

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "/login.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse response, Model model) {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String redirectUrl = request.getParameter(SsoConstants.REDIRECT_PARAM_NAME);

        if (ToolUtil.isEmpty(userName) || ToolUtil.isEmpty(password) || ToolUtil.isEmpty(redirectUrl)) {
            model.addAttribute(LOGIN_TIPS, "请求信息不完整!");
            return "/login.html";
        } else {

            /**
             * 判断用户账号密码是否正确
             */
            Integer userId = authService.checkUserLogin(userName, password);
            if (userId != null) {

                //如果账号密码正确,跳转回业务系统的url
                String token = authService.createToken(userId);
                request.getSession().setAttribute(SsoConstants.SESSION_LOGIN_FLAG, token);

                try {
                    response.sendRedirect(redirectUrl + "?" + SsoConstants.TOKEN_PARAM_NAME + "=" + token);
                    return null;
                } catch (IOException e) {
                    model.addAttribute(LOGIN_TIPS, "网络异常!");
                    return "/login.html";
                }
            } else {
                //如果账号密码错误
                model.addAttribute(SsoConstants.REDIRECT_PARAM_NAME, redirectUrl);
                model.addAttribute(LOGIN_TIPS, "账号或密码错误!");
                return "/login.html";
            }
        }
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String token() {
        return "暂未登录";
    }

    @RequestMapping(LOGOUT_URL)
    public String logout(HttpServletRequest request, Model model) {

        //销毁自己的session
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        //向各个sso客户端发送请求,告诉这个人已经退出
        authService.logoutAllSsoClients(request.getParameter(SsoConstants.TOKEN_PARAM_NAME));

        //跳转到登录页面
        String redirectUrl = request.getParameter(SsoConstants.REDIRECT_PARAM_NAME);
        model.addAttribute(SsoConstants.REDIRECT_PARAM_NAME, redirectUrl);
        return "/login.html";
    }
}