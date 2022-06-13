package com.rose.yaj.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.QuestionAndAnswerList;
import com.rose.yaj.dto.QuestionFormSubmit;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanDataDiscovery;
import com.rose.yaj.entity.YanQuestion;
import com.rose.yaj.entity.YanQuestionTag;
import com.rose.yaj.service.YanAnswerService;
import com.rose.yaj.service.YanQuestionService;
import com.rose.yaj.service.YanQuestionTagService;
import com.rose.yaj.util.JwtTokenUtil;
import com.rose.yaj.util.Util;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rosejava
 * @since 2020-10-04
 */
@Slf4j
@RestController
@RequestMapping("/yaj/yan-question")
@Api(tags = "问题相关接口" , description = "YanQuestionController | 问题模块")
public class YanQuestionController {


    @Autowired
    private YanQuestionService yanQuestionService;
    @Autowired
    private YanAnswerService yanAnswerService;
    @Autowired
    private YanQuestionTagService yanQuestionTagService;

    @ApiOperation("获取首页的数据")
    @GetMapping("/getDataDiscoveryPage")
    public GenericResponse getDataDiscoveryPage(@RequestParam("pageNo") Integer pageNo) throws Exception {


        List<YanDataDiscovery>  yanDataDiscoveries = yanQuestionService.getIndexData();

        return GenericResponse.response(ServiceError.NORMAL, yanDataDiscoveries);
    }


    @ApiOperation("获取问题以及回答")
    @GetMapping("/getQuestionAnswer")
    public GenericResponse getQuestionAnswer(@RequestParam("id") Integer id) throws Exception {
        QuestionAndAnswerList queAnswerList = yanQuestionService.getQuestionAnswer(id);

        return GenericResponse.response(ServiceError.NORMAL, queAnswerList);
    }

    @ApiOperation("提交回答")
    @PostMapping("/questionSubmit")
    public GenericResponse questionSubmit(
            @RequestBody QuestionFormSubmit form,
            HttpServletRequest request
            ) throws Exception {
        String openid = Util.fromRequestToOpenid(request);
        yanQuestionService.saveQue(openid,form);

        return GenericResponse.response(ServiceError.NORMAL);

    }
    @ApiOperation("列出所有标签")
    @GetMapping("/listTags")
    public GenericResponse questionSubmit() throws Exception {
        List<YanQuestionTag> list = yanQuestionTagService.list(new QueryWrapper<YanQuestionTag>());
        return GenericResponse.response(ServiceError.NORMAL,list);

    }
    @ApiOperation("getMyQue")
    @GetMapping("/getMyQue")
    public GenericResponse getMyQue( HttpServletRequest request) throws Exception {
        String openid = Util.fromRequestToOpenid(request);
        List<YanQuestion> list = yanQuestionService.getMyQue(openid);

        return GenericResponse.response(ServiceError.NORMAL,list);

    }

}















