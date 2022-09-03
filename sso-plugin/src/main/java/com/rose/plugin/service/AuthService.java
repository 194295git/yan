package com.rose.plugin.service;



import com.rose.plugin.model.LoginUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * auth服务接口
 *
 * @author fengshuonan
 * @date 2018-02-03 22:56
 */
public interface AuthService {

    /**
     * 验证账号密码是否正确(验证成功返回用户id,验证失败返回null)
     *
     * @author stylefeng
     * @Date 2018/2/3 22:58
     */
    Integer checkUserLogin(String userName, String password);

    /**
     * 创建授权令牌
     *
     * @author stylefeng
     * @Date 2018/2/3 23:13
     */
    String createToken(Integer userId);

    /**
     * 验证token是否正确(true-验证成功,false-验证失败)
     *
     * @author stylefeng
     * @Date 2018/2/3 23:14
     */
    boolean checkToken(HttpServletRequest request, String token, String clientAddr);

    /**
     * 通过token获取登录用户
     *
     * @author stylefeng
     * @Date 2018/2/3 23:18
     */
    LoginUser getLoginUserByToken(String token);

    /**
     * 通过用户id获取用户详细信息
     *
     * @author stylefeng
     * @Date 2018/2/4 10:52
     */
    LoginUser getLoginUserByUserId(Integer userId);

    /**
     * 记录哪些客户端在服务端进行过校验
     */
    void recordSSOClient(String clientAddr);

    /**
     * 获取所有sso客户端地址
     */
    Set<String> getAllSsoClientUrl();

    /**
     * 向所有sso客户端发送退出信号
     */
    void logoutAllSsoClients(String token);

}
