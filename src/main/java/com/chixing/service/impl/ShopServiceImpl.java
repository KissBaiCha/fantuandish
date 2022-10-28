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
    public Page<Shop> getByPage(Integer pageNum) {
        Page<Shop> page = new Page<>(pageNum,4);
        return shopMapper.selectPage(page,null);
    }

    @Override
    public Page<Shop> getByFoodType(String foodType) {
        Page<Shop> page = new Page<>(1,4);
        cleanQueryWrapper();
        foodQueryWrapper.eq("food_type",foodType);
        List<Food> foods = foodMapper.selectList(foodQueryWrapper);
        Set<Integer> collect = foods.stream()
                .map(Food::getShopId)
                .collect(Collectors.toSet());
        shopQueryWrapper.in("shop_id",collect);
        return shopMapper.selectPage(page,shopQueryWrapper);
    }

    @Override
    public Page<Shop> getByShopAvgCost(Float shopMinCost,Float shopMaxCost) {
        cleanQueryWrapper();
        if (shopMinCost!=null)
            shopQueryWrapper.ge("shop_avg_cost",shopMinCost);
        if (shopMaxCost!=null)
            shopQueryWrapper.le("shop_avg_cost",shopMaxCost);
        Page<Shop> page = new Page<>(1,4);
        return shopMapper.selectPage(page,shopQueryWrapper);
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
    public Page<Shop> getByPrice(Integer pageNum) {
        shopQueryWrapper.clear();
        shopQueryWrapper.orderByDesc("shop_avg_cost");
        Page<Shop> page = new Page<>(pageNum,4);
        return shopMapper.selectPage(page,shopQueryWrapper);
    }

    @Override
    public Page<Shop> getByScore(Integer pageNum) {
        shopQueryWrapper.clear();
        shopQueryWrapper.orderByDesc("shop_score");
        Page<Shop> page = new Page<>(pageNum,4);
        return shopMapper.selectPage(page,shopQueryWrapper);
    }

    @Override
    public Page<Shop> getTest(Integer pageNum, String foodType,String foodPrice,String foodSort) {
        cleanQueryWrapper();
        Page<Shop> page = new Page<>(pageNum,4);
        System.out.println("类型"+foodType+"价格"+foodPrice);
        if (foodType!=null){
            System.out.println("类型不为空");
            foodQueryWrapper.eq("food_type",foodType);
            List<Food> foods = foodMapper.selectList(foodQueryWrapper);
            Set<Integer> collect = foods.stream()
                    .map(Food::getShopId)
                    .collect(Collectors.toSet());
            shopQueryWrapper.in("shop_id",collect);
        }
        if (foodPrice!=null){
            System.out.println("价格不为空");
            if (foodPrice.endsWith("元以上")){
                String maxPrice = foodPrice.substring(0,foodPrice.indexOf("元"));
                Float shopMaxCost = Float.valueOf(maxPrice);
                shopQueryWrapper.le("shop_avg_cost",shopMaxCost);
                System.out.println("元以上");
            }
            else if (foodPrice.endsWith("元以下")){
                String minPrice = foodPrice.substring(0,foodPrice.indexOf("元"));
                Float shopMinCost = Float.valueOf(minPrice);
                shopQueryWrapper.ge("shop_avg_cost",shopMinCost);
                System.out.println("元以下");
            }
            else {
                String minPrice =foodPrice.substring(0,foodPrice.indexOf("-"));
                String maxPrice =foodPrice.substring(foodPrice.indexOf("-"),foodPrice.indexOf("元"));
                Float shopMinCost = Float.valueOf(minPrice);
                Float shopMaxCost = Float.valueOf(maxPrice);
                shopQueryWrapper.ge("shop_avg_cost",shopMinCost);
                shopQueryWrapper.le("shop_avg_cost",shopMaxCost);
                System.out.println("元=====元");
            }
        }
//
//        if (sort!=null){
//            if (sort==1){
//                return shopMapper.selectPage(page,null);
//            }
//            if (sort==2){
//                shopQueryWrapper.orderByDesc("shop_avg_cost");
//            }
//            if (sort==3){
//                shopQueryWrapper.orderByDesc("shop_score");
//            }
//        }
        System.out.println("dddd");
        return shopMapper.selectPage(page,shopQueryWrapper);
    }


}
