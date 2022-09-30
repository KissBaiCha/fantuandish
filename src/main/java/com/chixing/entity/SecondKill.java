package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("second_kill")
public class SecondKill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀id
     */
    @TableId(value = "second_kill_id", type = IdType.AUTO)
    private Integer secondKillId;

    /**
     * 美食id
     */
    private Integer foodId;

    /**
     * 开始时间
     */
    private LocalDateTime secondKillStartTime;

    /**
     * 结束时间
     */
    private LocalDateTime secondKillEndTime;

    /**
     * 秒杀商品详情
     */
    private String secondKillDetail;

    /**
     * 秒杀价格
     */
    private BigDecimal secondKillPrice;

    /**
     * 库存
     */
    private Integer secondKillStock;

    /**
     * modcount
     */
    private Integer version;

    private String other1;

    private String orher2;

    /**
     * 创建时间	
     */
    private LocalDateTime secondKillCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime secondKillUpdateTime;
}
