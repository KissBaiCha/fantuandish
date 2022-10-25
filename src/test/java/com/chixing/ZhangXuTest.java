package com.chixing;

import com.chixing.commons.R;
import com.chixing.entity.Food;
import com.chixing.entity.MyOrder;
import com.chixing.service.IFoodCollectionService;
import com.chixing.service.IMyOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    @Test
    public void fun1(){
        myOrderService.save(1,2,2,true);

    }
}
