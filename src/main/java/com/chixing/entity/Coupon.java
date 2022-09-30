package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Data
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券id
     */
    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Integer couponId;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 优惠金额
     */
    private BigDecimal couponPrice;

    /**
     * 有效时间（天）
     */
    private Integer couponValidDays;

    /**
     * 有效时间（时）
     */
    private Integer couponValidHours;

    /**
     * 优惠条件
     */
    private BigDecimal couponCondition;

    /**
     * 优惠券描述
     */
    private String couponDetail;

    /**
     * 创建时间
     */
    private LocalDateTime couponCreateTime;

    /**
     * 更新时间
     */
    private LocalDateTime couponUpdateTime;

    private String other1;

    private String other2;
}
