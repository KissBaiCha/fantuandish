package com.chixing.entity.vo;

import com.chixing.entity.MyCoupon;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Xu Zhang
 * @date 2022/11/2
 */
@Data
public class MyCouponVO {
    private MyCoupon myCoupon;
    private BigDecimal couponPrice;
    private BigDecimal couponCondition;

    public MyCouponVO(MyCoupon myCoupon, BigDecimal couponPrice, BigDecimal couponCondition) {
        this.myCoupon = myCoupon;
        this.couponPrice = couponPrice;
        this.couponCondition = couponCondition;
    }

    public MyCouponVO() {
    }
}
