package com.rose.permission.oauth2;/**
 * @author rose
 * @create 2024/1/9
 */

/**
 * @author rose<br>
 */

import com.rose.permission.entity.AccessToken;
import com.rose.permission.entity.CommonUser;
import com.rose.permission.entity.RefreshToken;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TokenService {

    // 假设这是存储Token的地方，实际应用中可能是数据库或缓存系统
    private ConcurrentHashMap<String, AccessToken> accessTokenStore = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, RefreshToken> refreshTokenStore = new ConcurrentHashMap<>();

    // Token过期时间，这里简化为固定值
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 3600 * 1000; // 1小时
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 28 * 24 * 3600 * 1000; // 28天

    // 验证访问Token
    public CommonUser verifyAccessToken(String token) {
        AccessToken accessToken = accessTokenStore.get(token);
        if (accessToken != null && accessToken.getExpirationDate().after(new Date())) {
            // Token有效
            return getCommonUserFromAccessToken(accessToken);
        }
        // Token无效或过期
        return null;
    }

    // 刷新访问Token
    public AccessToken refreshAccessToken(String refreshToken) {
        RefreshToken storedRefreshToken = refreshTokenStore.get(refreshToken);
        if (storedRefreshToken != null && storedRefreshToken.getExpirationDate().after(new Date())) {
            // 刷新Token有效，生成新的访问Token
            CommonUser CommonUser = getCommonUserFromRefreshToken(storedRefreshToken);
            AccessToken newAccessToken = generateNewAccessToken(CommonUser);
            accessTokenStore.put(newAccessToken.getToken(), newAccessToken);
            return newAccessToken;
        }
        // 刷新Token无效或过期
        return null;
    }

    // 撤销Token
    public void revokeToken(String accessToken, String refreshToken) {
        // 移除访问Token
        accessTokenStore.remove(accessToken);
        // 移除刷新Token
        refreshTokenStore.remove(refreshToken);
    }

    // 生成新的访问Token
    private AccessToken generateNewAccessToken(CommonUser CommonUser) {
        String token = generateUniqueToken();
        Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME);
        AccessToken accessToken = new AccessToken(token, expirationDate);
        // 可以在这里关联用户信息和Token
        return accessToken;
    }

    // 从访问Token获取用户信息
    private CommonUser getCommonUserFromAccessToken(AccessToken accessToken) {
        // 实现Token到用户信息的映射逻辑
        // ...
        return null;
    }

    // 从刷新Token获取用户信息
    private CommonUser getCommonUserFromRefreshToken(RefreshToken refreshToken) {
        // 实现Token到用户信息的映射逻辑
        // ...
        return null;
    }

    // 生成唯一Token，实际应用中可能会更复杂
    private String generateUniqueToken() {
        return UUID.randomUUID().toString();
    }
}