package com.chixing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    //默认分页查询
    Page<Shop> getByPage(Integer pageNum);
//    //多条件筛选
//    Page<Shop> getByFoodType(String foodType);
//    Page<Shop> getByShopAvgCost(Float shopMinCost,Float shopMaxCost);
////    List<Shop> getBySift(String foodType,Float shopMinCost,Float shopMaxCost);
//    //价格排序
//    Page<Shop> getByPrice(Integer pageNum);
//    //评分排序
//    Page<Shop> getByScore(Integer pageNum);

    //整合
    Page<Shop> getBySift(Integer pageNum,String foodType,String foodPrice,String foodSort);
    //查询全部
    List<Shop> getAll();
}
