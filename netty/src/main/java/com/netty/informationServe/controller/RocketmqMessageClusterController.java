package com.netty.informationServe.controller;

import com.netty.common.entity.SendRequest;
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

    @ApiOperation(value="消息推送接口", notes="根据用户标识进行推送，返回不存在的用户")
    @RequestMapping(value="/message/send",method = RequestMethod.POST)
    public GenericResponse send(@RequestBody @Valid SendRequest request){
        GenericResponse result = null;
        Set notExist = messageService.execute(request);

        if(notExist!= null && notExist.size()>0){
            //存在找不到的客户端
            result =GenericResponse.response(ServiceError.NOT_EXIST_CLIENT);
//                    new GenericResponse(,notExist);
        }else{
            result = GenericResponse.response(ServiceError.NORMAL);
        }
        return result;
    }
}