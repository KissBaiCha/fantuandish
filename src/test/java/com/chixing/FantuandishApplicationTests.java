package com.chixing;

import com.chixing.entity.Coupon;
import com.chixing.entity.Shop;
import com.chixing.mapper.CouponMapper;
import com.chixing.service.IShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FantuandishApplicationTests {
    @Autowired
    IShopService shopService;
    @Test
    void contextLoads() {
        List<Shop> shops = shopService.getByFoodType("汉堡");
        System.err.println(shops.size());
        for (Shop shop : shops) {
            System.out.println(shop);
        }

    }

}
