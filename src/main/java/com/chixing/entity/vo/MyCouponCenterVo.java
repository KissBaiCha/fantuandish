package com.chixing.entity.vo;

import com.chixing.entity.MyCoupon;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Xu Zhang
 * @date 2022/11/7
 */
@Data
public class MyCouponCenterVo {
    //店铺ID
    private Integer shopId;
    //店铺名
    private String shopName;
    //我的优惠券对象
    private MyCoupon coupon;
    //优惠金额
    private BigDecimal couponPrice;

    public MyCouponCenterVo(Integer shopId, String shopName, MyCoupon coupon, BigDecimal couponPrice) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.coupon = coupon;
        this.couponPrice = couponPrice;
    }

    public MyCouponCenterVo() {
    }
}
