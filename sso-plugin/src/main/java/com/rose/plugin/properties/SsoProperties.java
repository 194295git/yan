package com.rose.plugin.properties;

/**
 * sso配置
 *
 * @author fengshuonan
 * @date 2018-02-03 20:53
 */
public class SsoProperties {

    public static final String BEETLCONF_PREFIX = "sso";

    /**
     * sso服务器的地址,以/结尾(不支持携带参数的url)
     */
    private String serverUrl;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
