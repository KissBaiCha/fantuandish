package com.chixing.controller;

import com.chixing.commons.R;
import com.chixing.entity.Customer;
import com.chixing.service.ICustomerService;
import com.chixing.util.JwtUtil;
import com.chixing.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/19 18:02
 */
@Controller
@RequestMapping("/customer")
@Slf4j
public class LoginController {
    @Autowired
    private ICustomerService customerService;

    @ResponseBody
    @PostMapping("login")
    public R<String> login(Customer customer, HttpServletRequest request) {
        log.info("login获得的用户名" + customer.getCustomerName());
        log.info("login获得的密码" + customer.getCustomerPwd());
        R<String> result = customerService.loginByName(customer);
        if (result.getCode() == 200) {
            HttpSession session = request.getSession();
            session.setAttribute("token", result.getData().get("token"));
            session.setMaxInactiveInterval(60 * 60);
        }
        return result;
    }

    @ResponseBody
    @PostMapping("loginByCode/{telno}/{verCode}")
    public R<String> loginByCode(@PathVariable(value = "telno",required = false) Long customerTelno,
                                 @PathVariable(value = "verCode",required = false) Integer code,
                                 HttpServletRequest request){
        Integer sessionCode = (Integer) request.getSession().getAttribute("vercode");
        R<String> result = customerService.loginByCode(customerTelno,code,sessionCode);
        if (result.getCode() == 200){
            HttpSession session = request.getSession();
            session.setAttribute("token", result.getData().get("token"));
            session.setMaxInactiveInterval(60 * 60);
        }
        System.out.println(result.getMessage());
        return result;
    }

}
