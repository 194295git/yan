package com.rose.yaj.config;

import com.rose.yaj.entity.YanUser;
import com.rose.yaj.service.YanUserService;
import com.rose.yaj.service.impl.YanUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//注册自定义的拦截器
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                //排除静态资源
                .excludePathPatterns(
                        "/**/*.html",
                        "/**/*.js",
                        "/**/*.css",
                        "/swagger-ui.html");
    }
}
