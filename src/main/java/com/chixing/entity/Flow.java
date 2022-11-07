package com.chixing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水id
     */
    @TableId
    private Integer flowId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 流水号
     */
    private String flowNumber;

    /**
     * 流水金额（实际支付金额）
     */
    private BigDecimal flowPrice;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    private Integer flowStatus;

    /**
     * 创建时间
     */
    private LocalDateTime flowCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime flowUpdateTime;

    private String other1;

    private String other2;
}
