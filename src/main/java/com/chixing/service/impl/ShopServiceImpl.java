package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Food;
import com.chixing.entity.Shop;
import com.chixing.mapper.FoodMapper;
import com.chixing.mapper.ShopMapper;
import com.chixing.service.IShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
public class ShopServiceImpl implements IShopService {
    @Resource
    private ShopMapper shopMapper;
    @Resource
    private FoodMapper foodMapper;
    private QueryWrapper<Shop> shopQueryWrapper = new QueryWrapper<>();
    private QueryWrapper<Food> foodQueryWrapper = new QueryWrapper<>();
    private void cleanQueryWrapper(){
        foodQueryWrapper.clear();
        shopQueryWrapper.clear();
    }
    @Override
    public Shop getById(Integer shopId) {
        return shopMapper.selectById(shopId);
    }

    @Override
    public int save(Shop shop) {
        return shopMapper.insert(shop);
    }

    @Override
    public int update(Shop shop) {
        return shopMapper.updateById(shop);
    }

    @Override
    public int remove(Integer shopId) {
        return shopMapper.deleteById(shopId);
    }

    @Override
    public List<Shop> getByPage(Integer pageNum) {
        Page<Shop> page = new Page<>(pageNum,4);
        return shopMapper.selectPage(page,null).getRecords();
    }

    @Override
    public List<Shop> getByFoodType(String foodType) {
        cleanQueryWrapper();
        foodQueryWrapper.eq("food_type",foodType);
        List<Food> foods = foodMapper.selectList(foodQueryWrapper);
        Set<Integer> collect = foods.stream()
                .map(Food::getShopId)
                .collect(Collectors.toSet());
        shopQueryWrapper.in("shop_id",collect);
        return shopMapper.selectList(shopQueryWrapper);
    }

    @Override
    public List<Shop> getByShopAvgCost(Float shopMinCost,Float shopMaxCost) {
        cleanQueryWrapper();
        shopQueryWrapper.between("shop_avg_cost",shopMinCost,shopMaxCost);
        List<Shop> shopList = shopMapper.selectList(shopQueryWrapper);
        return shopList;
    }


    @Override
    public List<Shop> getBySift(String foodType,Float shopMinCost,Float shopMaxCost){
        cleanQueryWrapper();
        foodQueryWrapper.eq("food_type",foodType);
        List<Food> foods = foodMapper.selectList(foodQueryWrapper);
        Set<Integer> collect = foods.stream()
                .map(Food::getShopId)
                .collect(Collectors.toSet());
        shopQueryWrapper.in("shop_id",collect).between("shop_avg_cost",shopMinCost,shopMaxCost);
        return shopMapper.selectList(shopQueryWrapper);
    }

    @Override
    public List<Shop> getByPrice() {
        shopQueryWrapper.clear();
        shopQueryWrapper.orderByDesc("shop_avg_cost");
        Page<Shop> page = new Page<>(1,4);
        return shopMapper.selectPage(page,shopQueryWrapper).getRecords();
    }

    @Override
    public List<Shop> getByScore() {
        shopQueryWrapper.clear();
        shopQueryWrapper.orderByDesc("shop_score");
        Page<Shop> page = new Page<>(1,4);
        return shopMapper.selectPage(page,shopQueryWrapper).getRecords();
    }


}
