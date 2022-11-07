package com.chixing.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Food;
import com.chixing.entity.MyOrder;
import com.chixing.entity.Shop;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    boolean updStatus(String orderId);

    /**
     * 根据用户Id分页查询所有
     * @param pageNum 页码
     * @param cusId 用户Id
     * @param status 订单状态
     * @return 订单数据
     */
    Page<MyOrder> getByPage(Integer pageNum,Integer cusId,Integer status);


    LocalDateTime getOrderDateTime(String orderId);

    //订单商品
    Map<MyOrder,Shop> getOrderShop(Integer customerId);
}
