package com.chixing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
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
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;

    /**
     * 用户名
     */
    private String customerName;

    /**
     * 用户密码
     */
    private String customerPwd;

    /**
     * 用户电话
     */
    private Long customerTelno;

    /**
     * 用户状态
     */
    private Integer customerStatus;

    /**
     * 注册时间
     */
    private LocalDate customerCreateDate;

    /**
     * 头像
     */
    private String customerHeadImg;

    /**
     * 更改时间
     */
    private LocalDate customerUpdateDate;

    private String other1;

    private String other2;

    /**
     * 创建时间
     */
    private LocalDateTime customCteateTime;

    /**
     * 更新时间
     */
    private LocalDateTime customUpdateTime;
}
