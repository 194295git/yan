package com.rose.yaj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.AnswerDto;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.service.YanAnswerService;
import com.rose.yaj.service.YanUserService;
import com.rose.yaj.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/yaj/yan-answer")
@Api(tags = "回答相关接口" , description = "YanAnswerController | 回答模块")
public class YanAnswerController {

    @Autowired
    private YanAnswerService yanAnswerService;

    @Autowired
    private YanUserService yanUserService;

    @ApiOperation("根据id获取Answer")
    @GetMapping("/getAnswer")
    public GenericResponse getAnswer(@RequestParam("id") Long id) throws Exception {
//        YanAnswer item = yanAnswerService.getById(id);
        //原先的这种方法可能是需要是主键的
        QueryWrapper<YanAnswer> wrapper = new QueryWrapper<>();
        wrapper.eq("answer_id", id);
        YanAnswer item = yanAnswerService.getOne(wrapper);
        item.setAvatarUrl(yanUserService.getAvatarUrlByOpenid(item.getOpenid()));

        return GenericResponse.response(ServiceError.NORMAL, item);
    }

    @ApiOperation("writeAnswer")
    @PostMapping("/writeAnswer")
    public GenericResponse writeAnswer(HttpServletRequest request,
                                       @RequestBody AnswerDto.AnswerSubmit answerSubmit) throws Exception {
        String openid = Util.fromRequestToOpenid(request);
        // 参数必须有谁写的，写的哪个问题,写了那些东西
        yanAnswerService.writeAnswer(openid,answerSubmit);

        return GenericResponse.response(ServiceError.NORMAL);
    }

//    @PostMapping("/getAnswerSubmit")
//    public GenericResponse getAnswerSubmit(String submit) throws Exception {
//
//        AnswerSubmit req =  JSONObject.parseObject(submit,AnswerSubmit.class);
//        String token = req.getToken();
//        Claims claims = JwtTokenUtil.parseJWT(token);
//        String id = (String)claims.get("id");
//        System.out.println(id);
//
//        YanAnswer yanAnswer = new YanAnswer();
//        yanAnswer.setOpenid(id);
//
//        BeanUtils.copyProperties(req,yanAnswer);
//
//        boolean save = yanAnswerService.save(yanAnswer);
//        if (save ){
//            return GenericResponse.response(ServiceError.NORMAL);
//        }else {
//            return GenericResponse.response(ServiceError.INSERT_ERROR);
//        }
//
//    }






}

