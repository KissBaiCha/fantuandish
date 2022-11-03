package com.chixing.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Food;
import com.chixing.entity.MyOrder;
import com.chixing.entity.Shop;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
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
    String save(Integer cusId,Integer myCouponId,Integer foodId,Boolean isSecondKill);
    boolean update(MyOrder myOrder);
    //分页查询
    Page<MyOrder> getByPage(Integer pageNum);

    LocalDateTime getOrderDateTime(String orderId);

}
