package com.rose.yaj;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rose.yaj.dto.QuestionFormSubmit;
import com.rose.yaj.entity.YanAnswer;
import com.rose.yaj.entity.YanQuestion;
import com.rose.yaj.entity.YanQuestionTag;
import com.rose.yaj.entity.YanUser;
import com.rose.yaj.mapper.YanQuestionMapper;
import com.rose.yaj.mapper.YanUserMapper;
import com.rose.yaj.service.YanQuestionService;
import com.rose.yaj.service.YanUserService;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestUser extends DemoApplicationTests {
//    @Autowired
//    private YanUserMapper yanUserMapper;

//    @Test
//    public void contextLoads1() {
//        List<YanUser> yanUsers = yanUserMapper.selectList(null);
//        System.out.println(yanUsers);
//
//
//    }
    @Autowired
    YanUserService yanUserService;
    @Autowired
    YanQuestionService yanQuestionService;
    @Autowired
    YanQuestionMapper yanQuestionMapper;
    @Test
    public void test(){
        String  a= "1879878-NKCNO-NKNK";
        System.out.println( a != a );
    }

    public static void main(String[] args) {
        String  a= "1879878-NKCNO-NKNK";
        System.out.println( a != a );
    }
    @Data
    private class Yan{
        private String queTitle;
        private String queContent;
        private String avatarUrl;
        private String growth;
    }
    @Test
    public void tets(){
        List<YanQuestion> list = yanQuestionService.list(new QueryWrapper<YanQuestion>());
        List<YanUser> users = yanUserService.list(new QueryWrapper<YanUser>());
//        System.out.println(users);
        List<Yan> collect = list.stream().map(
                (item) -> {
                    Yan yan = new Yan();
                    BeanUtils.copyProperties(item, yan);
                    for (YanUser user : users) {
                        String openid = user.getOpenid();
                        if ( item.getOpenid() == null) return yan;
                        if (item.getOpenid().equals(openid)) {
                            System.out.println(user.getAvatarUrl());
                            yan.setAvatarUrl(user.getAvatarUrl());
                            yan.setGrowth(user.getMajor());
                        }

                    }
                    return yan;

                }
        ).collect(Collectors.toList());
        for (Yan yan : collect) {
            System.out.println(yan);
        }
//        System.out.println();
    }

    @Test
    public void tets2(){
        List<YanQuestion> list = yanQuestionService.list(new QueryWrapper<YanQuestion>());
//        System.out.println(users);
        List<Yan> collect = list.stream().map(
                (item) -> {
                    Yan yan = new Yan();
                    BeanUtils.copyProperties(item, yan);
                        if ( item.getOpenid() == null) return yan;
                    YanUser user = yanUserService.getOne(new QueryWrapper<YanUser>().eq("openid", item.getOpenid()));
                    if (user != null) {
                        yan.setAvatarUrl(user.getAvatarUrl());
                        yan.setGrowth(user.getMajor());
                    }
                    return yan;
                }
        ).collect(Collectors.toList());
        for (Yan yan : collect) {
            System.out.println(yan);
        }
//        System.out.println();
    }

    @Test
    public void test3(){
        List<Map<String, Object>> maps = yanQuestionMapper.testMap();
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }
    @Test
    public void test4(){
        List<QuestionFormSubmit.Yan> maps = yanQuestionMapper.testMap2();
        for (QuestionFormSubmit.Yan map : maps) {
            System.out.println(map);
        }

    }
    @Test
    public void test5(){
        System.out.println(yanUserService);

    }

}
