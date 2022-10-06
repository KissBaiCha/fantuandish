package com.chixing.mapper;

import com.chixing.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface ShopMapper extends BaseMapper<Shop> {
    List<Shop> selectByFoodType(String foodType);
    List<Shop> selectByShopAvgCost(Float shopMinCost,Float shopMaxCost);
    List<Shop> selectBySift(String foodType,Float shopMinCost,Float shopMaxCost);

}
