package com.chixing;

import com.chixing.commons.IGlobalCache;
import com.chixing.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/20 19:23
 */
@SpringBootTest
@Slf4j
public class ZhangXuTest {
    @Autowired
    IFoodCollectionService foodCollectionService;
    @Autowired
    IMyOrderService myOrderService;
    @Autowired
    IEvaluationService evaluationService;
    @Autowired
    IMyCouponService myCouponService;
    @Autowired
    ICouponService couponService;
    @Autowired
    IGlobalCache iGlobalCache;

    @Test
    public void fun1(){
        iGlobalCache.set("coupon:101",couponService.getById(1),20);
    }
}
