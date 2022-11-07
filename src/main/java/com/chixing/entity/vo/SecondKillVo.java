package com.chixing.entity.vo;

import java.math.BigDecimal;

public class SecondKillVo {
    private Integer secondKillId;
    private String foodMainImg;
    private String foodName;
    private BigDecimal secondKillPrice;
    private BigDecimal foodPrice;
    private Integer secondKillStock;

    public SecondKillVo(Integer secondKillId, String foodMainImg, String foodName, BigDecimal secondKillPrice, BigDecimal foodPrice, Integer secondKillStock) {
        this.secondKillId = secondKillId;
        this.foodMainImg = foodMainImg;
        this.foodName = foodName;
        this.secondKillPrice = secondKillPrice;
        this.foodPrice = foodPrice;
        this.secondKillStock = secondKillStock;
    }

    public Integer getSecondKillId() {
        return secondKillId;
    }

    public void setSecondKillId(Integer secondKillId) {
        this.secondKillId = secondKillId;
    }

    public String getFoodMainImg() {
        return foodMainImg;
    }

    public void setFoodMainImg(String foodMainImg) {
        this.foodMainImg = foodMainImg;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public BigDecimal getSecondKillPrice() {
        return secondKillPrice;
    }

    public void setSecondKillPrice(BigDecimal secondKillPrice) {
        this.secondKillPrice = secondKillPrice;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getSecondKillStock() {
        return secondKillStock;
    }

    public void setSecondKillStock(Integer secondKillStock) {
        this.secondKillStock = secondKillStock;
    }
}
