package com.chixing;


import com.chixing.entity.MyOrder;
import com.chixing.service.IMyOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
public class OrderTest {

    @Autowired
    private IMyOrderService myOrderService;
    @Test
    public void getById(){
     MyOrder myOrder = myOrderService.getById("1");
        System.out.println(myOrder);
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
//       boolean result = myOrderService.remove("1001");
//        System.out.println(result);

    }




}
