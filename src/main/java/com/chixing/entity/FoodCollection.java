package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime foodCollectionCreateTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime foodCollectionUpdateTime;

    private String other2;

    private String other1;

    public FoodCollection(Integer foodId, Integer customerId, LocalDateTime foodCollectionCreateTime, LocalDateTime foodCollectionUpdateTime) {
        this.foodId = foodId;
        this.customerId = customerId;
        this.foodCollectionCreateTime = foodCollectionCreateTime;
        this.foodCollectionUpdateTime = foodCollectionUpdateTime;
    }

    public FoodCollection(Integer foodCollectionId, Integer foodId, Integer customerId, LocalDateTime foodCollectionCreateTime, LocalDateTime foodCollectionUpdateTime, String other2, String other1) {
        this.foodCollectionId = foodCollectionId;
        this.foodId = foodId;
        this.customerId = customerId;
        this.foodCollectionCreateTime = foodCollectionCreateTime;
        this.foodCollectionUpdateTime = foodCollectionUpdateTime;
        this.other2 = other2;
        this.other1 = other1;
    }
}
