package com.rose.yaj.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author rose
 * @create 2022/10/7
 */
@FeignClient("yan-loginUser")
public interface LoginUserFeign {


}
