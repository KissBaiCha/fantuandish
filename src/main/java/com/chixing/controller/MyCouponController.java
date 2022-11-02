package com.chixing.controller;

import com.chixing.commons.R;
import com.chixing.entity.MyCoupon;
import com.chixing.service.IMyCouponService;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.chixing.commons.ResultCodeEnum.DATA_EMPTY;

@RestController
@RequestMapping("/myCoupon")
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


    @GetMapping("/getById/{id}")
    public MyCoupon getById(@PathVariable("id")int myCouponId){
        return myCouponService.getById(myCouponId);
    }

//    @GetMapping("/getCoupon/{shopId}")
//    @ResponseBody
//    public R<List<MyCoupon>> getMyCoupon(@PathVariable("shopId")Integer shopId, HttpServletRequest request){
//        Integer cusId = JwtUtil.getCusIdBySession(request);
//        List<MyCoupon> myCouponByShopId = myCouponService.getMyCouponByShopId(1, shopId);
//        R<List<MyCoupon>> result = R.ok("couponList",myCouponByShopId);
//        if(myCouponByShopId == null){
//            result = R.fail(DATA_EMPTY);
//        }
//        return result;
//    }

    @GetMapping("/getMyCouponPrice/{id}")
    @ResponseBody
    public BigDecimal getCouponPriceByMyCouponId(@PathVariable("id") Integer id){
        return myCouponService.getCouponPriceByMyCouponId(id);
    }
}
