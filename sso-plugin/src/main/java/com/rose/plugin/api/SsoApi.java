package com.rose.plugin.api;

import com.rose.plugin.cache.ClientCache;
import com.rose.plugin.constants.SsoConstants;
import com.rose.plugin.model.LoginUser;
import com.rose.plugin.model.SsoResponse;
import com.rose.plugin.model.enums.ResponseStatus;
import com.rose.plugin.service.AuthService;
import com.rose.plugin.util.SsoSpringContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 单点登录token校验API
 *
 * @author fengshuonan
 * @date 2018-02-04 11:56
 */
@RestController
public class SsoApi {

    //token验证的url
    public static final String AUTH_TOKEN_URL = "/ssoApi/authToken";

    //清除局部
    public static final String CLEAR_TOKEN_URL = "/ssoApi/clearToken";

    /**
     * 验证token是否正确
     */
    @RequestMapping(AUTH_TOKEN_URL)
    public SsoResponse authToken(HttpServletRequest request) {
        AuthService authService = SsoSpringContextHolder.getBean(AuthService.class);
        String token = request.getParameter(SsoConstants.TOKEN_PARAM_NAME);
        String clientAddr = request.getParameter(SsoConstants.CLIENT_REQUEST_ADDR_PARAM_NAME);

        boolean flag = authService.checkToken(request, token, clientAddr);

        if (flag) {
            LoginUser loginUser = authService.getLoginUserByToken(token);
            return SsoResponse.success(loginUser.getId());
        } else {
            return new SsoResponse(ResponseStatus.WRONG_TOKEN);
        }
    }

    /**
     * 清除客户端的token
     */
    @RequestMapping(CLEAR_TOKEN_URL)
    public SsoResponse clearToken(HttpServletRequest request) {
        String token = request.getParameter(SsoConstants.TOKEN_PARAM_NAME);
        ClientCache clientCache = SsoSpringContextHolder.getBean(ClientCache.class);
        clientCache.addInvalidKey(token);
        return SsoResponse.success();
    }
}
