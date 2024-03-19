package com.rose.yaj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.entity.YanMajorMe;
import com.rose.yaj.entity.YanMajorQuestion;
import com.rose.yaj.service.YanMajorMeService;
import com.rose.yaj.service.YanMajorQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/yaj/yan-rem")
public class RemQuestionConrtoller {

    @Autowired
    YanMajorQuestionService yanMajorQuestionService;

    @Autowired
    YanMajorMeService yanMajorMeService;


    @GetMapping("/getMajorMe")
    public GenericResponse getMajorMe() throws Exception {
        List<YanMajorMe> list =  yanMajorMeService.getMajorMe();
        return GenericResponse.response(ServiceError.NORMAL,list);
    }


    //要么就约好以数字和逗号形式的分割传递过来
    @GetMapping("/getQuestionAndType")
    public GenericResponse getDataDiscovery(@RequestParam String major_id) throws Exception {

        String majorId = "1,2,3,";
        String[] split = major_id.split(",");

        QueryWrapper<YanMajorQuestion> wrapper = new QueryWrapper<>();

        wrapper.in("major_id", Arrays.asList(split));
        List<YanMajorQuestion> item = yanMajorQuestionService.list(wrapper);

        List<String> questionList = new ArrayList<>();
        for (YanMajorQuestion yanMajorQuestion : item) {

            questionList.add(yanMajorQuestion.getQuestion());
        }

        System.out.println(questionList);

        List<String> test1 = new ArrayList<>();
        List<String> test2 = new ArrayList<>();

        for (String s : questionList) {
            String[] split1 = s.split("/");
            test1.add(split1[0]);
            test2.add(split1[1]);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("question",test1);
        data.put("type",test2);

        return GenericResponse.response(ServiceError.NORMAL, data);
    }



}
