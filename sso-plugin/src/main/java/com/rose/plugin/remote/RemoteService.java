package com.rose.plugin.remote;

import com.rose.plugin.api.SsoApi;
import com.rose.plugin.model.SsoResponse;
import com.rose.plugin.model.enums.ResponseStatus;

import com.rose.plugin.constants.SsoConstants;

import com.rose.plugin.properties.SsoProperties;
import org.springframework.web.client.RestTemplate;

/**
 * sso远程调用服务
 *
 * @author fengshuonan
 * @date 2018-02-04 15:47
 */
public class RemoteService {

    RestTemplate restTemplate;

    SsoProperties ssoProperties;

    public RemoteService(RestTemplate restTemplate, SsoProperties ssoProperties) {
        this.restTemplate = restTemplate;
        this.ssoProperties = ssoProperties;
    }

    /**
     * 请求sso server,验证token是否正确
     */
    public Integer validateToken(String token, String clientAddr) {
        String ssoUrl = ssoProperties.getServerUrl() + SsoApi.AUTH_TOKEN_URL + "?" + SsoConstants.TOKEN_PARAM_NAME + "=" + token + "&" + SsoConstants.CLIENT_REQUEST_ADDR_PARAM_NAME + "=" + clientAddr;
        SsoResponse ssoResponse = restTemplate.postForObject(ssoUrl, null, SsoResponse.class);

        if (ssoResponse == null) {
            return null;
        } else {
            if (ResponseStatus.SUCCESS.getCode().equals(ssoResponse.getCode())) {
                return ssoResponse.getUserId();
            } else {
                return null;
            }
        }
    }
}
