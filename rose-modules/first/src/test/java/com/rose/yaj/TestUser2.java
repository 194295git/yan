package com.rose.yaj;

import com.rose.yaj.service.YanUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rose
 * @create 2021/8/18
 */
public class TestUser2  extends DemoApplicationTests{

    @Autowired
    YanUserService yanUserService;

    @Test
    public void test5(){
        System.out.println(yanUserService);

    }
}
