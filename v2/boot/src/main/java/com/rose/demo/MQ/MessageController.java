package com.rose.demo.MQ;



import com.alibaba.fastjson.JSONObject;
import com.rose.demo.common.GenericResponse;
import com.rose.demo.common.ServiceError;

import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageController {



    @Autowired
    private RabbitTemplate rabbitTemplate; //rabbitTemplate是springboot 提供的默认实现



//    @ResponseBody
//    @PostMapping("/push")
    public void defaultMessage(@RequestBody ChatDto chatDto,
                               HttpServletRequest request)  throws IOException{

        String message = chatDto.getContent();
        String toUserId = chatDto.getEmail();
//        String openid = Util.fromRequestToOpenid(request);
        Map<String, String> map = new HashMap<String, String>();
        map.put("message",message);
        map.put("toUserId",toUserId);
//        map.put("openid",openid);
        System.out.println("是否走到了MessageController");
        rabbitTemplate.convertAndSend("fanoutExchange", "", JSONObject.toJSONString(map));
    }


    //todo  这个地方说明一下以前websocket的逻辑
    //第一版是通过WebSocketServer直接发送消息 然后前端接受了之后再返回
    //第二版实验的是推送消息的时候先交给mq处理 然后mq ack了之后再通过WebSocketServer处理

    //第三版的处理  channelRead0 现在有点看不懂 是收到消息后然后去通过mq去传达消息，去做后续的保存等操作

//    作者：诗圣_杜甫
//    链接：https://juejin.cn/post/6844904138749247502
//    来源：掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    @ApiOperation("聊天核心方法 待改进")
    @PostMapping("/push")
    public GenericResponse pushToWeb(@RequestBody ChatDto chatDto,
                                     HttpServletRequest request) throws IOException {

        String message = chatDto.getContent();
        String toUserId = chatDto.getEmail();

        Integer rs = WebSocketServer.sendInfo(message, toUserId);

//        String openid = Util.fromRequestToOpenid(request);
//        yanUserChatService.saveChat(openid,chatDto,rs);

        return  GenericResponse.response(ServiceError.NORMAL,"消息已经成功发送");
    }



}
