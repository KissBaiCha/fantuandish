package com.chixing;

import com.chixing.entity.Coupon;
import com.chixing.mapper.CouponMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FantuandishApplicationTests {
    @Autowired
    CouponMapper couponMapper;
    @Test
    void contextLoads() {
        Coupon coupon = couponMapper.selectById(10);
        System.out.println(coupon);
    }

}
