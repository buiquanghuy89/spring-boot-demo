package com.demo.spring.boot.controller;

import com.demo.spring.boot.exception.SpringException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bqhuy on 5/14/2018.
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(SpringException.class)
    public ModelAndView handleCustomException(SpringException ex) {
        ModelAndView model = new ModelAndView("exception");
//        model.addObject("errCode", ex.getExceptionMsg());
        model.addObject("exception", ex);
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error/generic_error");
        model.addObject("errMsg", "this is Exception.class");
        return model;
    }
}
