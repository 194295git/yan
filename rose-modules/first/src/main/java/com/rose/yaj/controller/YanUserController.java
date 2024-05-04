package com.rose.yaj.controller;


import com.rose.common.feignDto.RegisterFeign;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.dto.UserDto;
import com.rose.yaj.service.WeChatService;
import com.rose.yaj.service.YanUserChatService;
import com.rose.yaj.service.YanUserService;
import com.rose.yaj.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rosejava
 * @since 2020-10-01
 */
@RestController
@RequestMapping("/yaj/yan-user")
@Slf4j
@Api(tags = "用户相关接口" , description = "YanUserController | 用户模块")
public class YanUserController {

    @Autowired
    private YanUserService yanUserService;

    @Autowired
    private YanUserChatService yanUserChatService;
    @Autowired
    private WeChatService weChatService;

    @ApiOperation("注册用户表")
    @PostMapping(value="/registByOpenid")
    public GenericResponse registByOpenid(@RequestBody RegisterFeign dto){
        return weChatService.registByOpenid(dto);
    }

    /**
     *
     * @param openid 如果有openid ，则查询当前openid 的账号信息，如果没有openid，则查询其他用户的信息
     * @param request
     * @return
     */
    @ApiOperation("通过获取自己的详细信息")
    @GetMapping("/getUserInfo")
    public GenericResponse getUserInfo(@RequestParam(value="openid",required=false) String openid,
                                       HttpServletRequest request){
        if (openid != null){
            UserDto.UserInfo info =  yanUserService.getUserInfo(openid);
            return GenericResponse.response(ServiceError.NORMAL,info);
        }else {
            String openidSelf = Util.fromRequestToOpenid(request);
            UserDto.UserInfo info =  yanUserService.getUserInfo(openidSelf);
            return GenericResponse.response(ServiceError.NORMAL,info);
        }
    }


    @ApiOperation("通过token获得已登录用户的详细信息 | 要求登录")
    @GetMapping("/getUserInfoMe")
    public GenericResponse getUserInfo(
                                       HttpServletRequest request){
            String openidSelf = Util.fromRequestToOpenid(request);
            UserDto.UserInfo info =  yanUserService.getUserInfo(openidSelf);
            return GenericResponse.response(ServiceError.NORMAL,info);

    }


    @ApiOperation("通过当前用户关注的列表 | 要求登录")
    @GetMapping("/eyeUser")
    public GenericResponse queryEyeUser(HttpServletRequest request){

        String openid = Util.fromRequestToOpenid(request);

        List<UserDto.EyeUser> list =  yanUserService.queryEyeUser(openid);
        return GenericResponse.response(ServiceError.NORMAL,list);
    }


    @ApiOperation("筛选当前在线用户")
    @GetMapping("/liveUser")
    public GenericResponse queryLiveUser(){

        List<UserDto.LiveUser> list =  yanUserService.queryLiveUser();
        return GenericResponse.response(ServiceError.NORMAL,list);
    }

    /**
     * 更改在线状态
     * @param alive 1代表在线 0代表离线
     * @return
     * @throws IOException
     */
    @ApiOperation("更改在线状态 1代表在线 0代表离线")
    @GetMapping("/live")
    public GenericResponse changeLiveStatus(
            @RequestParam("alive") Integer alive,
            HttpServletRequest request) throws IOException {
        String openid = Util.fromRequestToOpenid(request);
        yanUserService.changeLiveStatus(openid,alive);
        return GenericResponse.response(ServiceError.NORMAL);
    }

    /**
     * 获取聊天记录的内容,并且将消息设置为已读；
     * @param toOpenid
     * @param request
     * @return
     * @throws IOException
     */
    @ApiOperation("获取聊天记录的内容,并且将消息设置为已读")
    @GetMapping("/getChatContent")
    public GenericResponse getChatContent(
            @RequestParam("toOpenid") String toOpenid,
            HttpServletRequest request) throws IOException {
        String openid = Util.fromRequestToOpenid(request);

        List<ChatDto.PageChat> yanUserChat = yanUserChatService.listByOpenid(openid,toOpenid);
        return GenericResponse.response(ServiceError.NORMAL,yanUserChat);
    }

    @ApiOperation("获取所有的聊天内容，拉取消息")
    @GetMapping("/getChatContentAll")
    public GenericResponse getChatContentAll(
            @RequestParam("msgid") String msgid,
            HttpServletRequest request) throws IOException {
        String openid = Util.fromRequestToOpenid(request);

        List<ChatDto.PageChat> yanUserChat = yanUserChatService.listByOpenidAll(openid,msgid);
        return GenericResponse.response(ServiceError.NORMAL,yanUserChat);
    }


    @ApiOperation("完善用户详细信息")
    @PostMapping("/addInfo")
    public GenericResponse getChatContent(
            @RequestBody UserDto.AddInfo addInfo,
            HttpServletRequest request) throws IOException {
        String openid = Util.fromRequestToOpenid(request);

        yanUserService.addInfo(openid,addInfo);
        return GenericResponse.response(ServiceError.NORMAL);
    }


    @ApiOperation("获取头像信息")
    @GetMapping("/getAvatarUrlByOpenid")
    public GenericResponse getAvatarUrlByOpenid(
            @RequestParam("openid") String openid
            ) throws IOException {

        String avatarUrlByOpenid = yanUserService.getAvatarUrlByOpenid(openid);
        return GenericResponse.response(ServiceError.NORMAL,avatarUrlByOpenid);
    }

    @ApiOperation("获取所有头像信息")
    @GetMapping("/getAvatarUrlAll")
    public GenericResponse getAvatarUrlAll( ) throws IOException {

        List<Map> rs = yanUserService.getAvatarUrlAll();
        return GenericResponse.response(ServiceError.NORMAL,rs);
    }







}

