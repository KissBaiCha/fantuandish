package com.chixing.controller;



import com.chixing.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomweMessageController {

    @Autowired
    private ICustomerService customerService;


}
