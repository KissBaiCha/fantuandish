package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private LocalTime shopOpenTime;

    /**
     * 打烊时间
     */
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
    private LocalDateTime shopCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime shopUpdateTime;
}
