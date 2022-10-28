package com.chixing.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MyOrderController {


    @GetMapping("/order")
    public ModelAndView getOrder(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/pay/order_pay.html");
        return modelAndView;
    }


}
