package com.rose.plugin.config;


import com.rose.plugin.api.SsoApi;
import com.rose.plugin.util.SsoSpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sso自动配置
 *
 * @author fengshuonan
 * @date 2018-02-04 12:16
 */
@Configuration
public class SsoAutoConfiguration {

    @Bean
    public SsoSpringContextHolder springContextHolder() {
        return new SsoSpringContextHolder();
    }

    @Bean
    public SsoApi ssoAuthApi() {
        return new SsoApi();
    }
}
