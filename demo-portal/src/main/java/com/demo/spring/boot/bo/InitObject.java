package com.demo.spring.boot.bo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.Object;

/**
 * Created by bqhuy on 3/14/2018.
 */
public class InitObject implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {

        System.out.println("BeforeInitialization : " + beanName);
        return bean;  // you can return any other object as well
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {

        System.out.println("AfterInitialization : " + beanName);
        return bean;  // you can return any other object as well
    }
}
