package com.chixing.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Customer;
import com.chixing.entity.MyOrder;
import com.chixing.entity.vo.MyOrderVo;
import com.chixing.service.ICustomerService;
import com.chixing.service.IFlowService;
import com.chixing.service.IFoodService;
import com.chixing.service.IMyOrderService;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class MyFanTuanController {
    @Autowired
    private IMyOrderService myOrderService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IFlowService flowService;

    /**
     * 个人信息访问方法
     * @param request
     * @return
     */
    @GetMapping("/user_details")
    public ModelAndView fantuan(HttpServletRequest request){
        Integer cusId = JwtUtil.getCusIdBySession(request);
        String cusName = JwtUtil.getCusNameBySession(request);
        Customer customer = customerService.getCustomerById(cusId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("cusName",cusName);
        modelAndView.setViewName("root/personal_center/user_msg");
        return modelAndView;
    }

    /**
     * 个人订单中心访问
     * @param request 请求
     * @return 订单数据
     */
    @GetMapping("/getAllOrder/{pageNum}")
    public ModelAndView getByPage(@PathVariable("pageNum")Integer pageNum,
                                  HttpServletRequest request){
        //订单ID
        Integer cusId = JwtUtil.getCusIdBySession(request);
        String cusName = JwtUtil.getCusNameBySession(request);
        ModelAndView mav = new ModelAndView();
        Page<MyOrder> myOrderData = myOrderService.getByPage(pageNum, cusId);
        List<MyOrderVo> MyOrderVoList = new ArrayList<>();
        for (MyOrder myOrder : myOrderData.getRecords()) {
            MyOrderVo myOrderVo = new MyOrderVo();
            myOrderVo.setMyOrder(myOrder);
            myOrderVo.setFood(foodService.getById(myOrder.getFoodId()));
            myOrderVo.setFlow(flowService.getByOrderNum(myOrder.getOrderId()));
            MyOrderVoList.add(myOrderVo);
        }
        mav.addObject("cusName",cusName);
        mav.addObject("MyOrderVoList",MyOrderVoList);
        mav.setViewName("root/personal_center/allorder");
        return mav;
    }



}
