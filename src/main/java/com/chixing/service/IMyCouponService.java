package com.chixing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.MyCoupon;
import com.chixing.entity.vo.MyCouponVO;


import java.math.BigDecimal;
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
    /**
     * 根据myCouponId获得优惠券优惠金额
     * @param myCouponId myCouponId
     * @return 优惠金额
     */
    BigDecimal getCouponPriceByMyCouponId(Integer myCouponId);
    List<MyCoupon> getByPage(Integer pageNum);
    /**
     * 查询用户当前店铺可使用优惠券
     * @param cusId 用户Id
     * @param shopId 店铺Id
     * @return List<MyCoupon>为当前店铺可使用优惠券
     */
    List<MyCouponVO> getMyCouponByShopId(Integer cusId, Integer shopId);

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

    /**
     * 根据用户Id分页查询所有
     * @param pageNum
     * @param cusId
     * @param status
     * @return
     */
    Page<MyCoupon> getByPage(Integer pageNum, Integer cusId, Integer status);
}
