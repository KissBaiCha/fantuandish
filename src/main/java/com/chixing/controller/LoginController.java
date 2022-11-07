package com.chixing.controller;

import com.chixing.commons.R;
import com.chixing.entity.Customer;
import com.chixing.service.ICustomerService;
import com.chixing.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public R<String> login(Customer customer,HttpServletRequest request){
        log.info("login获得的用户名" + customer.getCustomerName());
        log.info("login获得的密码" + customer.getCustomerPwd());
        R<String> result = customerService.loginByName(customer);
        if(result.getCode() == 200){
            HttpSession session = request.getSession();
            session.setAttribute("token",result.getData().get("token"));
            session.setMaxInactiveInterval(60*60);
        }
        return result;
    }


}
