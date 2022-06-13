package com.rose.yaj.controller;


import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.entity.YanAnswerLike;
import com.rose.yaj.service.YanAnswerLikeService;
import com.rose.yaj.util.Util;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/yaj/yan-answer-like")
public class LikeController {

    @Autowired
    YanAnswerLikeService yanAnswerLikeService;


    @PostMapping("/changeAnswerLikeStatus/{id}")
    public GenericResponse changeAnswerLikeStatus(@PathVariable("id") String  id,
                                        HttpServletRequest request) throws Exception {
        String openid = Util.fromRequestToOpenid(request);
        yanAnswerLikeService.changeAnswerLikeStatus(openid,id);

        return GenericResponse.response(ServiceError.NORMAL);


    }
}
