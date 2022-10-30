package com.chixing.controller;


import com.chixing.entity.Food;
import com.chixing.service.IFoodService;
import com.chixing.service.IMyOrderService;
import com.chixing.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class MyOrderController {
    @Autowired
    private IFoodService iFoodService;
    @Autowired
    private IMyOrderService myOrderService;
    @GetMapping("/order")
    public ModelAndView getOrder(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/pay/order_pay.html");
        return modelAndView;
    }

    @GetMapping("/order/{pageNum}")
    public ModelAndView getByPage(@PathVariable("pageNum") Integer pageNum){
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject();
        return null;
    }
    @PostMapping("/order")
    public ModelAndView creatOrder(@RequestParam("foodId") Integer foodId, HttpServletRequest request){
        Integer cusId = JwtUtil.getCusIdBySession(request);
        Food food = iFoodService.getById(foodId);
        String orderNum = myOrderService.save(cusId, null, foodId, false);
        ModelAndView modelAndView = new ModelAndView();
        log.info("==========="+foodId);
        modelAndView.addObject("food",food);
        modelAndView.addObject("orderNum",orderNum);
        modelAndView.setViewName("root/pay/gopay");
        return modelAndView;
    }




}
