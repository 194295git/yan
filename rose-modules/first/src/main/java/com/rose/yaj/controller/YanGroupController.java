package com.rose.yaj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.AnswerDto;
import com.rose.yaj.dto.ChatDto;
import com.rose.yaj.dto.GroupDto;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanGroup;
import com.rose.yaj.entity.YanUser;
import com.rose.yaj.service.YanGroupService;
import com.rose.yaj.service.YanUserChatService;
import com.rose.yaj.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author rose
 * @create 2022/6/8
 */
@RestController
@RequestMapping("/yaj/yan-group")
@Api(tags = "创建群聊相关接口" , description = "YanGroupController | 群聊模块")
public class YanGroupController {

    @Autowired
    YanGroupService yanGroupService;
    @Autowired
    YanUserChatService yanUserChatService;

    @ApiOperation("获取所有分组")
    @GetMapping("/getAllGroup")
    public GenericResponse getAllGroup() throws Exception {
        QueryWrapper<YanGroup> wrapper = new QueryWrapper<>();
        List<YanGroup> list = yanGroupService.list(wrapper);
        return GenericResponse.response(ServiceError.NORMAL, list);
    }
    @ApiOperation("获取指定id群聊下的所有用户openid")
    @GetMapping("/getGroupOpenid")
    public GenericResponse getGroupOpenid(@RequestParam("id") Integer id) throws Exception {
        List<String> res = yanGroupService.getGroupOpenid(id);
        return GenericResponse.response(ServiceError.NORMAL, res);
    }

    @ApiOperation("获取指定id群聊下的所有用户的详细信息")
    @GetMapping("/getGroupMemberDetail")
    public GenericResponse getGroupMemberDetail(@RequestParam("id") Integer id) throws Exception {
        List<YanUser> user = yanGroupService.getGroupMemberDetail(id);

        return GenericResponse.response(ServiceError.NORMAL, user);

    }

    @ApiOperation("创建群聊")
    @PostMapping("/addGroup")
    public GenericResponse addGroup(HttpServletRequest request
                                , @RequestBody  YanGroup yanGroup) throws Exception {
        String openid = Util.fromRequestToOpenid(request);
        // 参数必须有谁写的，写的哪个问题,写了那些东西
        Boolean res = yanGroupService.addGroup(openid, yanGroup);

        return GenericResponse.response(ServiceError.NORMAL,res);
    }

    @ApiOperation("添加群组成员")
    @PostMapping("/addGroupMember")
    public GenericResponse addGroupMember(HttpServletRequest request
            , @RequestBody GroupDto.GroupMember groupMember) throws Exception {
        //TODO  第一步 需要检查是否是群主或者管理员才可以添加
//        String openid = Util.fromRequestToOpenid(request);

        Boolean res = yanGroupService.addGroupMember(groupMember);

        return GenericResponse.response(ServiceError.NORMAL,res);
    }


    @ApiOperation("获取聊天记录的内容,并且将消息设置为已读")
    @GetMapping("/getGroupContent")
    public GenericResponse getGroupContent(
            @RequestParam("toGroup") String toGroup,
            HttpServletRequest request) throws IOException {
        String openid = Util.fromRequestToOpenid(request);

        List<ChatDto.PageChat> yanUserChat = yanUserChatService.listByOpenid2(openid,toGroup);
        return GenericResponse.response(ServiceError.NORMAL,yanUserChat);
    }





}
