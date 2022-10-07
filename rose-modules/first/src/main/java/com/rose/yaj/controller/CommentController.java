package com.rose.yaj.controller;

import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.CommentList;
import com.rose.yaj.service.YanAnswerCommentService;
import com.rose.yaj.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/yaj/yan-answer-comment")
@Api(tags = "评论相关接口" , description = "CommentController | 评论模块")
public class CommentController {

    @Autowired
    private YanAnswerCommentService yanAnswerCommentService;


    /**
     *
     * @param id
     * @param params isTop，comment，relation_user
     * @return
     * @throws Exception
     */
    @ApiOperation("根据id添加评论")
    @PostMapping("/addComment/{id}")
    public GenericResponse addComment(@PathVariable("id") String  id,
                                     @RequestBody Map<String, Object> params,
                                     HttpServletRequest request) throws Exception {

        String openid = Util.fromRequestToOpenid(request);
        yanAnswerCommentService.addComment(id,openid,params);
        return GenericResponse.response(ServiceError.NORMAL);
    }
    @ApiOperation("根据id获取评论")
    @GetMapping("/getComment/{id}")
    public GenericResponse getComment(@PathVariable("id") Long  id) throws Exception {

        List<CommentList> commentLists = yanAnswerCommentService.getComment(id);
        return GenericResponse.response(ServiceError.NORMAL,commentLists);
    }
    @ApiOperation("根据id删除评论")
    @PostMapping("/delComment/{id}")
    public GenericResponse deleteComment(@PathVariable("id") Integer  id) throws Exception {

        yanAnswerCommentService.removeById(id);
        return GenericResponse.response(ServiceError.NORMAL);
    }
}
