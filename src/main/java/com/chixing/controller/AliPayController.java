package com.chixing.controller;

import com.chixing.service.IAliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/9/23 18:19
 */
@Controller
public class AliPayController {
    private IAliPayService aliPayService;
    @Autowired
    public void setAliPayService(IAliPayService aliPayService) {
        this.aliPayService = aliPayService;
    }

    @PostMapping("alipay/goAlipay")
    @ResponseBody
    public String goAliPay(@RequestParam("orderNum") String orderNum){
        System.err.println(orderNum);
        return aliPayService.goAliPay(orderNum);
    }
    @GetMapping("alipayReturnNotice")
    public ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletRequest response){
        return aliPayService.alipayReturnNotice(request,response);
    }
    @PostMapping("alipayNotifyNotice")
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response){
        return aliPayService.alipayNotifyNotice(request,response);
    }
}
