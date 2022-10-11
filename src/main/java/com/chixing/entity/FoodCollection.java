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
@TableName("food_collection")
public class FoodCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 美食收藏id
     */
    @TableId(value = "food_collection_id", type = IdType.AUTO)
    private Integer foodCollectionId;

    /**
     * 美食id
     */
    private Integer foodId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 收藏时间
     */
    private LocalDateTime foodCollectionCreateTime;

    /**
     * 更新时间
     */
    private LocalDateTime foodCollectionUpdateTime;

    private String other2;

    private String other1;
}
