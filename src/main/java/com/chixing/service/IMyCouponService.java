package com.chixing.service;

import com.chixing.entity.MyCoupon;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface IMyCouponService  {
    int save(MyCoupon myCoupon);
    int remove(int myCouponId);
    int update(MyCoupon myCoupon);
    MyCoupon getById(int myCouponId);
    List<MyCoupon> getByPage(Integer pageNum);
    List<MyCoupon> getMyCoupon(int customerId);
}
