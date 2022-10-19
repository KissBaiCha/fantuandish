package com.chixing.entity.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/18 16:43
 */
@Data
public class CustomerTokenVO {
    private Integer cusId;
    private String cusName;
    private Long customerTelno;
    /**
     * 注册时间
     */
    private LocalDate customerCreateDate;

    /**
     * 头像
     */
    private String customerHeadImg;

    public CustomerTokenVO(Integer cusId, String cusName, Long customerTelno, LocalDate customerCreateDate, String customerHeadImg) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.customerTelno = customerTelno;
        this.customerCreateDate = customerCreateDate;
        this.customerHeadImg = customerHeadImg;
    }

    public CustomerTokenVO() {
    }
}
