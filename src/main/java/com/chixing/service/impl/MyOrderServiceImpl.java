package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.Food;
import com.chixing.entity.MyOrder;
import com.chixing.entity.SecondKill;
import com.chixing.mapper.*;

import com.chixing.service.IMyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kaka
 * @since 2022-10-02
 */
@Service
public class MyOrderServiceImpl implements IMyOrderService {


    private final MyOrderMapper myOrderMapper;
    private final MyCouponMapper myCouponMapper;
    private final CouponMapper couponMapper;
    private final FoodMapper foodMapper;
    private final SecondKillMapper secondKillMapper;
    @Autowired
    public MyOrderServiceImpl(MyOrderMapper myOrderMapper, MyCouponMapper myCouponMapper,CouponMapper couponMapper,FoodMapper foodMapper,SecondKillMapper secondKillMapper) {
        this.myOrderMapper = myOrderMapper;
        this.myCouponMapper = myCouponMapper;
        this.couponMapper = couponMapper;
        this.foodMapper = foodMapper;
        this.secondKillMapper = secondKillMapper;
    }

    @Override
    public MyOrder getById(String orderId) {
        MyOrder myOrder = myOrderMapper.selectById(orderId);
        System.out.println("myOrder:" + myOrder);
        return myOrder;
    }

    @Override
    public List<MyOrder> getAllByCusId(Integer cusId) {
        QueryWrapper<MyOrder> myOrderWrapper = new QueryWrapper<>();
        myOrderWrapper.eq("customer_id",cusId);
        return myOrderMapper.selectList(myOrderWrapper);
    }

    @Override
    public boolean save(Integer cusId,Integer myCouponId ,Integer foodId, Boolean isSecondKill) {
        Food food = foodMapper.selectById(foodId);
        MyOrder myOrder = new MyOrder();
        String uuId = UUID.randomUUID().toString().replace("-", "");
        myOrder.setOrderId(uuId);
        myOrder.setOrderNumber(uuId);
        myOrder.setCustomerId(cusId);
        myOrder.setFoodId(foodId);
        myOrder.setOrderStatus(1);
        myOrder.setOrderCreateTime(LocalDateTime.now());
        myOrder.setOrderUpdateTime(LocalDateTime.now());
        if(myCouponId != null){
            myOrder.setCouponId(myCouponId);
            Integer couponId = myCouponMapper.selectById(myCouponId).getCouponId();
            BigDecimal couponPrice = couponMapper.selectById(couponId).getCouponPrice();
            myOrder.setCouponPrice(couponPrice);
        }
        if(isSecondKill){
            myOrder.setOrderType(1);
            QueryWrapper<SecondKill> secondKillQueryWrapper = new QueryWrapper<>();
            secondKillQueryWrapper.eq("food_id",foodId);
            SecondKill secondKill = secondKillMapper.selectOne(secondKillQueryWrapper);
            BigDecimal secondKillPrice = secondKill.getSecondKillPrice();
            myOrder.setOrderOnePrice(secondKillPrice);
            myOrder.setOrderPrice(secondKillPrice.subtract(myOrder.getCouponPrice()));
        }else{
            myOrder.setOrderType(0);
            myOrder.setOrderOnePrice(food.getFoodPrice());
            myOrder.setOrderPrice(food.getFoodPrice().subtract(myOrder.getCouponPrice()));
        }
        return myOrderMapper.insert(myOrder) > 0;
    }

    @Override
    public boolean update(MyOrder myOrder) {
        return myOrderMapper.updateById(myOrder) > 0;
    }

    @Override
    public boolean remove(String orderId) {
        return myOrderMapper.deleteById(orderId) >0;
    }
}
