package com.chixing.service;


import com.chixing.entity.Food;
import com.chixing.entity.MyOrder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kaka
 * @since 2022-10-02
 */
public interface IMyOrderService {
    MyOrder getById(String orderId);
    List<MyOrder> getAllByCusId(Integer cusId);
    boolean save(Integer cusId,Integer myCouponId,Integer foodId,Boolean isSecondKill);
    boolean update(MyOrder myOrder);
    boolean remove(String orderId);

}
