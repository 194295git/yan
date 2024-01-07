package com.netty.informationServe.feign;

import com.netty.informationServe.config.RoseFeignConfig;
import com.rose.common.base.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rose
 * @create 2024/1/6
 */

@FeignClient(value= "yan-loginUser",configuration = RoseFeignConfig.class)
public interface NettyMqFeign {

    @RequestMapping(value = "/auth/issuccess")
    public GenericResponse getAuth() ;
}
