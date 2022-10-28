package com.chixing.controller;


import com.chixing.service.IMyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MyFanTuanController {


    @Autowired
    private IMyOrderService myOrderService;


    @GetMapping("/fantuan")
    public ModelAndView fantuan(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("customer/personal_center/fantuan.html");
        return modelAndView;
    }

    @GetMapping("/getAllOrder/{orderId}")
    public ModelAndView getByPage(@PathVariable("orderId")String orderId){
        ModelAndView mav = new ModelAndView();
        mav.addObject("MyOrder",myOrderService.getById(orderId));
        mav.setViewName("allorder");
        return mav;
    }



}
