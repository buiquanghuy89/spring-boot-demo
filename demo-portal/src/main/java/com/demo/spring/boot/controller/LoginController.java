package com.demo.spring.boot.controller;

import com.demo.spring.boot.bo.User;
import com.demo.spring.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by bqhuy on 11/14/2016.
 * Request Param Example
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET)
    public String preparePage(ModelMap model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user
            , ModelMap model) {
        User obj = loginService.checkLogin(user);
        if (obj != null) {
            model.addAttribute("userName", obj.getUserName());
            model.addAttribute("email", obj.getEmail());
            model.addAttribute("role", obj.getRole());
            return "result";
        }
        return "error1";
    }
}