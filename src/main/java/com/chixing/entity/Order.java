package com.chixing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 美食id
     */
    private Integer foodId;

    /**
     * 0：正常订单  1：普通订单
     */
    private Integer orderType;

    /**
     * 1:已下单 2：已支付 3：已完成 0：已取消
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
    private LocalDateTime orderCreatTime;

    /**
     * 单价
     */
    private BigDecimal orderOnePrice;

    /**
     * 订单价格
     */
    private BigDecimal orderPrice;

    /**
     * 我领取的优惠id
     */
    private Integer couponId;

    /**
     * 优惠价格（减少）
     */
    private BigDecimal couponPrice;

    /**
     * 创建时间
     */
    private LocalDateTime orderCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime orderUpdateTime;

    private String other1;

    private String other2;
}
