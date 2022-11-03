package com.chixing.service;

import com.chixing.entity.Coupon;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface ICouponService {
    int save(Coupon coupon);
    int remove(int couponId);
    int update(Coupon coupon);
    Coupon getById(int couponId);

    /**
     * 根据商家ID查询其所发布的优惠券
     * @param shopId 商家ID
     * @return 优惠券List
     */
    List<Coupon> getByShopId(Integer shopId);
}
