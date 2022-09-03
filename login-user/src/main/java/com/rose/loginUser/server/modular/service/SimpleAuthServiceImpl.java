package com.rose.loginUser.server.modular.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import com.rose.loginUser.server.modular.entity.SysUser;
import com.rose.plugin.api.SsoApi;
import com.rose.plugin.constants.SsoConstants;
import com.rose.plugin.model.LoginUser;
import com.rose.plugin.model.SsoResponse;
import com.rose.plugin.service.AuthService;
import com.rose.plugin.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * auth服务接口最简单实现
 *
 * @author fengshuonan
 * @date 2018-02-03 22:56
 */
@Service
public class SimpleAuthServiceImpl implements AuthService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<String, Object> cache = new ConcurrentHashMap<>();

    private Set<String> ssoClients = new HashSet<>();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Integer checkUserLogin(String userName, String password) {

        //查询账号是否存在
        SysUser sysUser = null;
        List<SysUser> accounts = sysUserService.selectList(new EntityWrapper<SysUser>().eq("ACCOUNT", userName));
        if (accounts != null && accounts.size() > 0) {
            sysUser = accounts.get(0);
        } else {
            return null;
        }

        //校验账号密码是否正确
        String md5Hex = MD5Util.encrypt(password + sysUser.getSalt());
        if (md5Hex.equals(sysUser.getPassword())) {
            return sysUser.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public String createToken(Integer userId) {
        String token = IdWorker.get32UUID();
        LoginUser loginUserByUserId = this.getLoginUserByUserId(userId);
        cache.put(token, loginUserByUserId);
        return token;
    }

    @Override
    public boolean checkToken(HttpServletRequest request, String token, String clientAddr) {
        if (cache.containsKey(token)) {
            recordSSOClient(clientAddr);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public LoginUser getLoginUserByToken(String token) {
        return (LoginUser) cache.get(token);
    }

    @Override
    public LoginUser getLoginUserByUserId(Integer userId) {
        return sysUserService.getUserLoginInfo(userId);
    }

    @Override
    public void recordSSOClient(String clientAddr) {
        ssoClients.add(clientAddr);
    }

    @Override
    public Set<String> getAllSsoClientUrl() {
        return ssoClients;
    }

    @Override
    public void logoutAllSsoClients(String token) {
        for (String ssoClientUrl : ssoClients) {
            String url = ssoClientUrl + SsoApi.CLEAR_TOKEN_URL + "?" + SsoConstants.TOKEN_PARAM_NAME + "=" + token;

            try {
                restTemplate.getForObject(url, SsoResponse.class);
            } catch (Exception e) {
                log.error("发送客户端注销token请求出错!", e);
            }
        }
    }
}
