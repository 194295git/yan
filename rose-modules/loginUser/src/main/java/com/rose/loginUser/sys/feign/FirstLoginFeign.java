package com.rose.loginUser.sys.feign;

import com.rose.common.base.GenericResponse;
import com.rose.common.feignDto.RegisterFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(value="/yaj/yan-user/registByOpenid",method = RequestMethod.POST,
            headers = {"Content-Type=application/json;charset=UTF-8"})
    GenericResponse registByOpenid(@RequestBody RegisterFeign dto);
}
