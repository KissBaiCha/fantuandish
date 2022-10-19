package com.chixing.controller;

import com.chixing.commons.R;
import com.chixing.entity.Customer;
import com.chixing.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/19 18:02
 */
@Controller
@RequestMapping("/customer")
public class LoginController {
    @Autowired
    private ICustomerService customerService;
    @ResponseBody
    @PostMapping("login")
    public R<String> login(Customer customer){
        return customerService.loginByName(customer);
    }
    @ResponseBody
    @PostMapping("jiemi")
    public String jieMi(HttpServletRequest request){
        String token1 = request.getHeader("token");
        return "ok";
    }
}
