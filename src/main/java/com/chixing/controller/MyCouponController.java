package com.chixing.controller;

import com.chixing.entity.MyCoupon;
import com.chixing.service.IMyCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/mycoupon")
public class MyCouponController {

    @Autowired
    private IMyCouponService myCouponService;

    @PostMapping("/save")
    public String save(MyCoupon myCoupon){
        myCoupon.setMyCouponGetTime(LocalDateTime.now());
        myCoupon.setMyCouponLoseTime(LocalDateTime.now().plusHours(1));
        myCoupon.setMyCouponStatus(1);
        myCoupon.setMyCouponCteateTime(LocalDateTime.now());
        int row = myCouponService.save(myCoupon);
        return myCoupon.toString();
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id")int myCouponId){
        int row = myCouponService.remove(myCouponId);
        return "移除我的优惠券 " + myCouponId;
    }

    @PutMapping("/update")
    public String update(MyCoupon myCoupon){
        myCoupon.setMyCouponUpdateTime(LocalDateTime.now());
        int row = myCouponService.update(myCoupon);
        return myCoupon.toString();
    }

    @GetMapping("/getById/{id}")
    public MyCoupon getById(@PathVariable("id")int myCouponId){
        return myCouponService.getById(myCouponId);
    }

    @GetMapping("/getByPage/{pageNum}")
    public List<MyCoupon> getMyCoupon(@PathVariable("pageNum")Integer pageNum){
        return myCouponService.getByPage(pageNum);
    }

    @GetMapping("/getMyCoupon/{id}")
    public List<MyCoupon> getMyCoupon(@PathVariable("id")int customerId){
        return myCouponService.getMyCoupon(customerId);
    }
}
