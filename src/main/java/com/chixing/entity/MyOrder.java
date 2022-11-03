package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author kaka
 * @since 2022-10-02
 */
@TableName("my_order")
@Data
public class MyOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private String orderId;
    private String orderNumber;
    private Integer customerId;
    private Integer foodId;
    private Integer orderType;
    private Integer orderStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:DD:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateDeserializer.class)		// 反序列化
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime orderCreateTime;
    private LocalDateTime orderUpdateTime;
    private BigDecimal orderOnePrice;
    private BigDecimal orderPrice;
    private Integer couponId;
    private BigDecimal couponPrice;

    private String other1;

    private String other2;


}
