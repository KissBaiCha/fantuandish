package com.chixing;


import com.chixing.entity.MyOrder;
import com.chixing.service.IMyOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class OrderTest {

    @Autowired
    private IMyOrderService myOrderService;

    @Test
    public void save(){
        MyOrder myOrder = new MyOrder();
        myOrder.setOrderId("1002");
        myOrder.setOrderNumber("14");
        myOrder.setCustomerId(12);
        myOrder.setFoodId(13);


        int result = myOrderService.save(myOrder);
        System.out.println(result);

    }

    @Test
    public void getById(){
     MyOrder myOrder = myOrderService.getById("1001");
        System.out.println(myOrder);
    }


    @Test
    public void getAll(){
        List<MyOrder> orderList = myOrderService.getAll();
        System.out.println(orderList);

    }



    @Test
    public void update(){
        MyOrder myOrder = myOrderService.getById("1002");
        myOrder.setOrderType(1);
        myOrder.setCouponId(11);

        boolean result = myOrderService.update(myOrder);
        System.out.println(result);
    }


    @Test
    public void delete(){
       boolean result = myOrderService.remove("1001");
        System.out.println(result);


    }



}
