package com.chixing.controller;

import com.chixing.commons.R;
import com.chixing.entity.Customer;
import com.chixing.service.ICustomerService;
import com.chixing.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/customer")
public class RegisterController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("sendCode/{telno}")
    @ResponseBody
    public R<String> sendCode(@PathVariable("telno") Long customerTelno, HttpServletRequest request) {
                Integer verCode = SmsUtil.sendMsg(String.valueOf(customerTelno)).getData().get("code");
                HttpSession session = request.getSession();
                session.setAttribute("vercode", verCode);
//                session.setAttribute("vercode", 123123);
                session.setMaxInactiveInterval(60 * 5);
        return R.ok();
    }


    @PostMapping("register/{verCode}")
    @ResponseBody
    public String register(Customer customer, @PathVariable("verCode") Integer verCode, HttpServletRequest request) {
        Integer code = (Integer) request.getSession().getAttribute("vercode");
        return customerService.registerUser(customer, code, verCode);
    }
}
