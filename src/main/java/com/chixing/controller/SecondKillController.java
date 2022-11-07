package com.chixing.controller;

import com.chixing.service.ISecondKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/secondKill")
public class SecondKillController {
    @Autowired
    ISecondKillService secondKillService;

    @GetMapping("getAllPro")
    public ModelAndView getAllPro(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("skPro",secondKillService.getAllPro());
        mav.setViewName("secondkill/secondkill");
        return mav;
    }
}
