package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chixing.commons.R;
import com.chixing.entity.Food;
import com.chixing.entity.FoodCollection;
import com.chixing.mapper.FoodCollectionMapper;
import com.chixing.mapper.FoodMapper;
import com.chixing.service.IFoodCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.chixing.commons.ResultCodeEnum.NO_FOODCOLLECTION;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
public class FoodCollectionServiceImpl implements IFoodCollectionService {

    private final FoodCollectionMapper foodCollectionMapper;
    private FoodMapper foodMapper;
    @Autowired
    public FoodCollectionServiceImpl(FoodCollectionMapper foodCollectionMapper, FoodMapper foodMapper) {
        this.foodCollectionMapper = foodCollectionMapper;
        this.foodMapper = foodMapper;
    }

    @Override
    public R<String> saveByCusId(Integer cusId,Integer foodId) {
        FoodCollection foodCollection = new FoodCollection(foodId,cusId, LocalDateTime.now(),LocalDateTime.now());
        if(foodCollectionMapper.insert(foodCollection) > 0){
            return R.ok();
        }
        return R.fail();
    }

    @Override
    public R<List<Food>> getAllFoodColByCusId(Integer cusId) {
        QueryWrapper<FoodCollection> foodCollectionQueryWrapper = new QueryWrapper<>();
        foodCollectionQueryWrapper.eq("customer_id",cusId);
        List<FoodCollection> foodCollections = foodCollectionMapper.selectList(foodCollectionQueryWrapper);
        Set<Integer> foodIdSet = foodCollections.stream().map(FoodCollection::getFoodId).collect(Collectors.toSet());
        QueryWrapper<Food> foodQueryWrapper = new QueryWrapper<>();
        foodQueryWrapper.in("food_id",foodIdSet);
        List<Food> foods = foodMapper.selectList(foodQueryWrapper);
        if(foods.size() > 0){
            return R.ok("foodList",foods);
        }
        return R.fail(NO_FOODCOLLECTION);
    }

    @Override
    public R<String> removeById(Integer foodCollectionId) {
        if(foodCollectionMapper.deleteById(foodCollectionId) > 0){
            return R.ok();
        }
        return R.fail();
    }
}
