package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Data
@TableName("my_coupon")
public class MyCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 我的优惠券id
     */
    @TableId(value = "my_coupon_id", type = IdType.AUTO)
    private Integer myCouponId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 优惠券id
     */
    private Integer couponId;

    /**
     * 优惠券获取时间
     */
    private LocalDateTime myCouponGetTime;

    /**
     * 截止时间
     */
    private LocalDateTime myCouponLoseTime;

    /**
     * 1:未使用 2：已使用 0 ：已失效
     */
    private Integer myCouponStatus;

    /**
     * 创建时间
     */
    private LocalDateTime myCouponCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime myCouponUpdateTime;

    private String other1;

    private String other2;
}
