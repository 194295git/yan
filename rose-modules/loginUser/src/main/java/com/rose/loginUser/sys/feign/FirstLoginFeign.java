package com.rose.loginUser.sys.feign;

import com.rose.common.base.GenericResponse;
import com.rose.loginUser.sys.feign.dto.RegisterFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author rose
 * @create 2022/10/16
 */
@FeignClient("yan-first")
public interface FirstLoginFeign {

    /**
     * 引入first服务的接口.
     * @param dto
     * @return
     */
    @PostMapping(value="/registByOpenid")
    GenericResponse registByOpenid(@RequestBody RegisterFeign dto);
}
