package com.rose.demo.service;

import com.rose.demo.common.GenericResponse;
import org.springframework.stereotype.Service;

/**
 * @author rose
 * @create 2022/5/18
 */

public interface LoginService {

    GenericResponse regist(String email, String password,String username);
}
