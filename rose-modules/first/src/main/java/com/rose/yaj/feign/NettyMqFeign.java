package com.rose.yaj.feign;

import com.rose.common.base.GenericResponse;
import com.rose.common.mqutil.SendRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author rose
 * @create 2024/1/6
 */

@FeignClient("chatServe")
public interface NettyMqFeign {

    @ApiOperation(value="消息推送接口", notes="根据用户标识进行推送，返回不存在的用户")
    @RequestMapping(value="rocketmq/mqmessage/message/send",method = RequestMethod.POST)
    public GenericResponse send(@RequestBody @Valid SendRequest request);
}
