package com.chixing.controller;

import com.chixing.commons.R;
import com.chixing.entity.MyCoupon;
import com.chixing.service.IMyCouponService;
import com.chixing.util.JwtUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.chixing.commons.ResultCodeEnum.DATA_EMPTY;

@RestController
@RequestMapping("/myCoupon")
@Slf4j
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

    /**
     * 此方法用来改变优惠券使用状态
     * @param newCouponId 我的优惠券ID
     * @param channel 管道
     * @param message 信息
     */
    @RabbitHandler
    @RabbitListener(queues = "coupon-Queue")
    public void couponListener(Integer newCouponId, Channel channel , Message message){
        log.info("接收到了"+newCouponId+"优惠券的使用信息。正在更改状态");
        MyCoupon myCoupon = myCouponService.getById(newCouponId);
        myCoupon.setMyCouponStatus(2);
        myCouponService.update(myCoupon);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
