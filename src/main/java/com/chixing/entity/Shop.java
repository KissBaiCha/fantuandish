package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Data
@Document(indexName = "shop")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商店id
     */
    @Id
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;

    /**
     * 商店名
     */
    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_max_word")
    private String shopName;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date,format = DateFormat.date)//自动检测类型
    @JsonDeserialize(using = LocalDateDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateSerializer.class)		// 序列化
    private LocalDate shopCreateTime;

    /**
     * 省
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private String shopProvince;

    /**
     * 市
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private String shopCity;

    /**
     * 区/县
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private String shopArea;

    /**
     * 街道
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private String shopStreet;

    /**
     * 详细地址
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private String shopAddressDetail;

    /**
     * 电话
     */
    @Field(type = FieldType.Long)//自动检测类型
    private Long shopTelno;

    /**
     * 营业时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,pattern ="HH:mm:ss")
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    @JsonDeserialize(using = LocalTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalTimeSerializer.class)		// 序列化
    private LocalTime shopOpenTime;

    /**
     * 打烊时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom,pattern ="HH:mm:ss")
    @JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
    @JsonDeserialize(using = LocalTimeDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalTimeSerializer.class)		// 序列化
    private LocalTime shopCloseTime;

    /**
     * 评分
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private Double shopScore;

    /**
     * 人均消费
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private Double shopAvgCost;

    /**
     * 主图片
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private String shopMainImg;

    /**
     * 店铺详情
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private String shopDetail;

    /**
     * 经度
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private BigDecimal shopLongitude;

    /**
     * 纬度
     */
    @Field(type = FieldType.Auto)//自动检测类型
    private BigDecimal shopLatitude;

    @Field(type = FieldType.Auto)//自动检测类型
    private String other1;

    @Field(type = FieldType.Auto)//自动检测类型
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
