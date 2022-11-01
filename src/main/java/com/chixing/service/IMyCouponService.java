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
    Integer save(MyCoupon myCoupon);
    Integer update(MyCoupon myCoupon);
    MyCoupon getById(Integer myCouponId);
    List<MyCoupon> getByPage(Integer pageNum);
    /**
     * 查询用户当前店铺可使用优惠券
     * @param cusId 用户Id
     * @param shopId 店铺Id
     * @return List<MyCoupon>为当前店铺可使用优惠券
     */
    List<MyCoupon> getMyCouponByShopId(Integer cusId,Integer shopId);

    /**
     * 根据用户Id查询所有可用优惠券
     * @param cusId 用户Id
     * @return 当前用户所有可用优惠券
     */
    List<MyCoupon> getAvailableMyCoupon(Integer cusId);

    /**
     * 根据用户Id查询所有过期(失效)优惠券
     * @param cusId 用户Id
     * @return 当前用户所有过期(失效)优惠券
     */
    List<MyCoupon> getNotAvailableMyCoupon(Integer cusId);
}
