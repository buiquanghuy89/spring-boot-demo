package com.demo.spring.boot.service.impl;

import com.demo.spring.boot.bo.User;
import com.demo.spring.boot.config.AcmeProperties;
import com.demo.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by bqhuy on 3/19/2018.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AcmeProperties properties;

    @Override
    public boolean addUser(User user) {
        if (user != null) {
            System.out.println(user.toString());
            return true;
        }
        return false;
    }

    @Cacheable()
    public AcmeProperties getProperties() {
        return properties;
    }

    public void setProperties(AcmeProperties properties) {
        this.properties = properties;
    }
}
