package com.chixing.controller;

import com.chixing.entity.vo.SecondKillVo;
import com.chixing.service.ISecondKillService;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/secondKill")
public class SecondKillController {
    @Autowired
    ISecondKillService secondKillService;

    @GetMapping("/getAllPro")
    public ModelAndView getAllPro(HttpServletRequest request){
        String cusNameBySession = JwtUtil.getCusNameBySession(request);
        ModelAndView mav = new ModelAndView();
        mav.addObject("skPro",secondKillService.getAllPro());
        mav.addObject("cusName",cusNameBySession);
        mav.setViewName("secondkill/secondkill");
        return mav;
    }
    @PostMapping("/decrStock")
    public SecondKillVo decrStock(){
        return secondKillService.decreaseProductNumFromRedis(1);
    }
}
