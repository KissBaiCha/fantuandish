package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

public class Food implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 美食id
     */
    @TableId(value = "food_id", type = IdType.AUTO)
    private Integer foodId;

    /**
     * 店铺id
     */
    private Integer shopId;

    /**
     * 美食名
     */
    private String foodName;

    /**
     * 美食价格
     */
    private BigDecimal foodPrice;

    /**
     * 美食评分
     */
    private Double foodScore;

    /**
     * 美食详情
     */
    private String foodDetail;

    /**
     * 美食类型
     */
    private String foodType;

    /**
     * 美食图片
     */
    private String foodMainImg;

    /**
     * 美食推荐状态
     */
    private Integer foodRecommendStatus;

    /**
     * 创建时间
     */
    private LocalDateTime foodCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime foodUpdateTime;

    private String other1;

    private String other2;
}
