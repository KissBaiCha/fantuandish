package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
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
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商店id
     */
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;

    /**
     * 商店名
     */
    private String shopName;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateSerializer.class)		// 序列化
    private LocalDate shopCreateTime;

    /**
     * 省
     */
    private String shopProvince;

    /**
     * 市
     */
    private String shopCity;

    /**
     * 区/县
     */
    private String shopArea;

    /**
     * 街道
     */
    private String shopStreet;

    /**
     * 详细地址
     */
    private String shopAddressDetail;

    /**
     * 电话
     */
    private Long shopTelno;

    /**
     * 营业时间
     */
    @JsonDeserialize(using = LocalTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalTimeSerializer.class)		// 序列化
    private LocalTime shopOpenTime;

    /**
     * 打烊时间
     */
    @JsonDeserialize(using = LocalTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalTimeSerializer.class)		// 序列化
    private LocalTime shopCloseTime;

    /**
     * 评分
     */
    private Double shopScore;

    /**
     * 人均消费
     */
    private Double shopAvgCost;

    /**
     * 主图片
     */
    private String shopMainImg;

    /**
     * 店铺详情
     */
    private String shopDetail;

    /**
     * 经度
     */
    private BigDecimal shopLongitude;

    /**
     * 纬度
     */
    private BigDecimal shopLatitude;

    private String other1;

    private String other2;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateTimeSerializer.class)		// 序列化
    private LocalDateTime shopCteateTime;

    /**
     * 更新时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateTimeSerializer.class)		// 序列化
    private LocalDateTime shopUpdateTime;

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopCreateTime=" + shopCreateTime +
                ", shopProvince='" + shopProvince + '\'' +
                ", shopCity='" + shopCity + '\'' +
                ", shopArea='" + shopArea + '\'' +
                ", shopStreet='" + shopStreet + '\'' +
                ", shopAddressDetail='" + shopAddressDetail + '\'' +
                ", shopTelno=" + shopTelno +
                ", shopOpenTime=" + shopOpenTime +
                ", shopCloseTime=" + shopCloseTime +
                ", shopScore=" + shopScore +
                ", shopAvgCost=" + shopAvgCost +
                ", shopMainImg='" + shopMainImg + '\'' +
                ", shopDetail='" + shopDetail + '\'' +
                ", shopLongitude=" + shopLongitude +
                ", shopLatitude=" + shopLatitude +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", shopCteateTime=" + shopCteateTime +
                ", shopUpdateTime=" + shopUpdateTime +
                '}';
    }
}
