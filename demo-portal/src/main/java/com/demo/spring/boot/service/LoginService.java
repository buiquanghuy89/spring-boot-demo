package com.demo.spring.boot.service;

import com.demo.spring.boot.bo.User;

/**
 * Created by bqhuy on 3/20/2018.
 */
public interface LoginService {
    public User checkLogin(User user);
}
