package com.chixing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/19 13:54
 */
@Controller
@RequestMapping("/customer")
public class TestController {
    @GetMapping("login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login.html");
        return modelAndView;
    }
}
