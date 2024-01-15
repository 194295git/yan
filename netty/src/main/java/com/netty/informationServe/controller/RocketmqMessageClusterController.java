package com.netty.informationServe.controller;

import com.rose.common.netty.Commond;
import com.rose.common.mqutil.SendRequest;
import com.netty.informationServe.service.MessageService;
import com.rose.common.base.GenericResponse;
import com.rose.common.base.ServiceError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@Api(value = "MessageController")
@RequestMapping("rocketmq/mqmessage")
public class RocketmqMessageClusterController {

    @Autowired
    private MessageService messageService;


    /**
     * 目前这个接口是没有加上权限的，是可以随意调用的。需要处理一下吗；
     * 那就需要加上那个feign传递header的机制了；
     * 目前的netty 服务有nacos注册，有feign的接口调用。现在要加上shiro部分.相当安全了;
     * 鉴权的部分也是需要去调用feign的。需要点时间去处理(已经处理)
     * @param request
     * @return
     */
    @ApiOperation(value="消息推送接口", notes="根据用户标识进行推送，返回不存在的用户")
    @RequestMapping(value="/message/send",method = RequestMethod.POST)
    public GenericResponse send(@RequestBody @Valid SendRequest request){
        GenericResponse result = null;
        Set notExist = messageService.execute(request, Commond.HTTP_REQUEST);

        if(notExist!= null && notExist.size()>0){
            //存在找不到的客户端
            result =GenericResponse.response(ServiceError.NOT_EXIST_CLIENT,notExist);
//                    new GenericResponse(,notExist);
        }else{
            //客户端都存在
            result = GenericResponse.response(ServiceError.NORMAL);
        }
        return result;
    }
}
