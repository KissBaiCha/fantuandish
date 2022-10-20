package com.chixing.controller;

import com.chixing.entity.Coupon;
import com.chixing.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class   CouponController {

    @Autowired
    private ICouponService couponService;

    @PostMapping("/save")
    public String save(Coupon coupon){
        coupon.setCouponCreateTime(LocalDateTime.now());
        int row = couponService.save(coupon);
        return coupon.toString();
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable("id")int couponId){
        int row = couponService.remove(couponId);
        return "删除优惠券"+couponId;
    }

    @PutMapping("/update")
    public String update(Coupon coupon){
        coupon.setCouponUpdateTime(LocalDateTime.now());
        int row = couponService.update(coupon);
        return coupon.toString();
    }

    @GetMapping("/getById/{id}")
    public Coupon getById(@PathVariable("id")int couponId){
        return couponService.getById(couponId);
    }

    @GetMapping("/getByPage/{pageNum}")
    public List<Coupon> getByPage(@PathVariable("pageNum")Integer pageNum){
        return couponService.getByPage(pageNum);
    }

}
