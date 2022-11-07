package com.chixing.entity.vo;

import com.chixing.entity.MyCoupon;
import lombok.Data;

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

    public MyCouponCenterVo(Integer shopId, String shopName, MyCoupon coupon) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.coupon = coupon;
    }

    public MyCouponCenterVo() {
    }
}
