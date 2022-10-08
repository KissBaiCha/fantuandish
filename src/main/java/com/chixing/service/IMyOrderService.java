package com.chixing.service;


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
    List<MyOrder> getAll();
    int save(MyOrder myOrder);
    boolean update(MyOrder myOrder);
    boolean remove(String orderId);

}
