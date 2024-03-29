package com.rose.yaj.controller;


import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.service.YanUserChatService;
import com.rose.yaj.service.YanUserService;
import com.rose.yaj.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@Api(tags = "消息相关接口" , description = "MessageController | 消息模块")
public class MessageController {
    @Autowired
    YanUserService yanUserService;
    @Autowired
    YanUserChatService yanUserChatService;


//    作者：诗圣_杜甫
//    链接：https://juejin.cn/post/6844904138749247502
//    来源：掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//    @ApiOperation("聊天核心方法 待改进")
//    @PostMapping("/push")
//    public GenericResponse pushToWeb(@RequestBody ChatDto chatDto,
//                                            HttpServletRequest request) throws IOException {
//
//        String message = chatDto.getContent();
//        String toUserId = chatDto.getEmail();
//
//        Integer rs = WebSocketServer.sendInfo(message, toUserId);
//
//        String openid = Util.fromRequestToOpenid(request);
//        yanUserChatService.saveChat(openid,chatDto,rs);
//
//        return  GenericResponse.response(ServiceError.NORMAL,"消息已经成功发送");
//    }


    @ApiOperation("保存聊天内容")
    @PostMapping("/saveChatContent")
    public GenericResponse saveChatContent(@RequestBody ChatDto chatDto,
                                     HttpServletRequest request) throws IOException {
        //todo  已读和未读待做 需要和netty产生联动
        String openid = Util.fromRequestToOpenid(request);
        yanUserChatService.saveChat(openid,chatDto,1);

        return  GenericResponse.response(ServiceError.NORMAL,"消息已经成功发送");
    }




}
