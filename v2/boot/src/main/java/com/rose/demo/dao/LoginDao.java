package com.rose.demo.dao;

import com.rose.demo.entity.User;

/**
 * @author rose
 * @create 2022/5/18
 */

public interface LoginDao {
    int register(User user);

    User selectByEmail(String email);
}
