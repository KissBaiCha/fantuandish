package com.chixing.service;

import com.chixing.entity.Shop;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 * 查 get  增 save  改 update   删 remove
 */
public interface IShopService{
    Shop getById(Integer shopId);
    int save(Shop shop);
    int update(Shop shop);
    int remove(Integer shopId);

    //多条件筛选
    List<Shop> getByFoodType(String foodType);
    List<Shop> getByShopAvgCost(Float shopMinCost,Float shopMaxCost);
    List<Shop> getBySift(String foodType,Float shopMinCost,Float shopMaxCost);
}
