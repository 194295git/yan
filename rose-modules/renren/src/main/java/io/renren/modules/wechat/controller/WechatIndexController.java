package io.renren.modules.wechat.controller;

import io.renren.modules.wechat.constant.AccessToken;
import io.renren.modules.wechat.constant.ConstantWeChat;
import io.renren.modules.wechat.service.MessageService;
import io.renren.modules.wechat.utils.CommonWechatUtil;
import io.renren.modules.wechat.utils.SignUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author zhoumin
 * @project wechatPublicAccount
 * @create 2018-07-17 21:52
 */
@RestController
@RequestMapping("/index")
@Api(tags = "微信模块相关接口" , description = "WechatIndexController | 微信模块")
public class WechatIndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatIndexController.class);
    @Autowired
    private MessageService messageService;

    @RequestMapping("/test")
    public String test(){
        return "success";
    }





    /**
     * 在这个方法里面传入用户的openid，和消息就可以发送消息了；
     * 调用的话当其余地方发生火点的时候就调用这个接口；
     * @param keyword 某某地区
     * @return
     */
    @ApiOperation("根据opeind和关键字想指定用户发送请求")
    @GetMapping("/testMsg")
    public String test2(String keyword){
        String openid = "oeCtO5y9NKfFYFLuz_hBQsd8-No8";
        messageService.sendTemplate(openid,keyword);
        return "success";
    }


    @ApiOperation("获取accesToken")
    @GetMapping("/getAcessToken")
    public String getAcessToken(String keyword){
        String appId = ConstantWeChat.APPID;
        String appSecret = ConstantWeChat.APPSECRET;
        AccessToken at = CommonWechatUtil.getToken(appId, appSecret);
        return at.toString();
    }



    @ApiOperation("微信服务器验证自己的服务器")
    @RequestMapping(method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            out.close();
            out = null;
        }
    }

    @ApiOperation("处理微信发送过来的请求")
    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
//        String respMessage = weixinPost(request);
        String respMessage = messageService.newMessageRequest(request);

        // 响应消息
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(respMessage);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            out.close();
            out = null;
        }
    }


}
