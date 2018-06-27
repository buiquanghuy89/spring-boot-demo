package com.demo.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bqhuy on 11/14/2016.
 * Redirect Example
 */
@Controller
public class RedirectController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/redirect/login", method = RequestMethod.GET)
    public String authorInfo(Model model) {
        // Todo something here
//        return "redirect:/login";
        return "redirect:/pa";
    }
}
