package com.rose.yaj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.common.ServiceError;
import com.rose.yaj.dto.AnswerDto;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanDataDiscovery;
import com.rose.yaj.entity.YanMajorMe;
import com.rose.yaj.entity.YanMajorQuestion;
import com.rose.yaj.service.YanMajorMeService;
import com.rose.yaj.service.YanMajorQuestionService;
import com.rose.yaj.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

        // todo  接下来做的是把question和type提取出来就可以了 然后以R形式返回

        wrapper.in("major_id", Arrays.asList(split));
        List<YanMajorQuestion> item = yanMajorQuestionService.list(wrapper);
        System.out.println(item);


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
        System.out.println(test1);
        System.out.println(test2);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("question",test1);
        data.put("type",test2);

        return GenericResponse.response(ServiceError.NORMAL, data);
    }



}
