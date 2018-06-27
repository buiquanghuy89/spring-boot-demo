package com.demo.spring.boot.service.impl;

import com.demo.spring.boot.bo.User;
import com.demo.spring.boot.config.AcmeProperties;
import com.demo.spring.boot.exception.SpringException;
import com.demo.spring.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bqhuy on 3/20/2018.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AcmeProperties acmeProperties;

    @Override
    public User checkLogin(User user) {
        if (user.getUserName() == null || user.getUserName().equals("")) {
            throw new SpringException("UserName is requirement");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new SpringException("Password is requirement");
        }
        if (acmeProperties != null
                && acmeProperties.getSecurity() != null
                && acmeProperties.getSecurity().getUsers() != null) {
            for (User obj : acmeProperties.getSecurity().getUsers()) {
                if (obj.getUserName().equals(user.getUserName())
                        && obj.getPassword().equals(user.getPassword())) {
                    return obj;
                }
            }
        }
        return null;
    }
}
