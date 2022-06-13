package com.rose.yaj.controller;



import com.rose.yaj.common.GenericResponse;
import com.rose.yaj.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {
    // 消费者没有 Service 层，那就需要使用 Rest 去请求
    // 那么肯定有一个 RestTemplate 模板,这里面肯定有很多供我们调用的方法
    // 既然这样就需要把它注册到 Spring 中

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 提供者 URL 的前缀
     */
//    private static final String REST_URL_PREFIX = "http://localhost:8000";


//    @RequestMapping("/consumer/getDeptById/{id}")
//    public String  getDeptById(@PathVariable("id")Long id){
//        // 去提供者那里，通过 Get 请求获取一个对象
//        // getForObject 的参数：提供者地址；【请求所需的参数】 ； 返回值对象的类
//        // 请求需要传递的参数；
//        //          1. 通过URL拼接；
//        //          2. 使用 Map 传参 ；
//        //          3. 直接传递参数对象（就像下面的 Add 方法）
//        return restTemplate.getForObject(REST_URL_PREFIX+"/queryDeptById"+id,String.class);
//    }

    @RequestMapping("/getMajor")
    public GenericResponse getMajor(@RequestParam String text) throws Exception {
        String url = Util.getURL();
        Map<String, String> map = new HashMap<String, String>();
        map.put("text", text);
        //url  返回值 传入的参数
        ResponseEntity<GenericResponse> responseEntity = restTemplate.getForEntity(url+"/api?text={text}", GenericResponse.class, map);
        return responseEntity.getBody();
    }


}