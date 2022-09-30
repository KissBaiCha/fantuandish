package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评价id
     */
    @TableId(value = "eva_id", type = IdType.AUTO)
    private Integer evaId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 美食id
     */
    private Integer foodId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 评分
     */
    private Double evaScore;

    /**
     * 评价内容
     */
    private String evaContent;

    /**
     * 评价时间
     */
    private LocalDateTime evaDateTime;

    /**
     * 点赞人数
     */
    private Long praiseNum;

    /**
     * 创建时间
     */
    private LocalDateTime evaCreateTime;

    /**
     * 更新时间
     */
    private LocalDateTime evaUpdateTime;

    private String other1;

    private String other2;
}
