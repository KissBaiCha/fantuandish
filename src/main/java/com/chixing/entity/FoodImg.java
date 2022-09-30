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
@TableName("food_img")
public class FoodImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 美食图片id
     */
    @TableId(value = "food_img_id", type = IdType.AUTO)
    private Integer foodImgId;

    /**
     * 店铺id
     */
    private Integer foodId;

    /**
     * 图片路径
     */
    private String foodImgPath;

    /**
     * 状态  0:有效 1：无效
     */
    private Integer imgStatus;

    /**
     * 创建时间
     */
    private LocalDateTime foodImgCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime foodImgUpdateTime;

    private String other1;

    private String other2;
}
