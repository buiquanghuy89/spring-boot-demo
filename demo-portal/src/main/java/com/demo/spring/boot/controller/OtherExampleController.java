package com.demo.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bqhuy on 11/14/2016.
 */
@Controller
public class OtherExampleController {

    @RequestMapping("/somePath")
    public String requestResponseExample(HttpServletRequest request
            , HttpServletResponse reponses
            , Model model) {
        // Todo something here
        return "someView";
    }
}
