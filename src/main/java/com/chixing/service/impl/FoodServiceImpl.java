package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Food;
import com.chixing.entity.SecondKill;
import com.chixing.entity.Shop;
import com.chixing.mapper.FoodMapper;
import com.chixing.mapper.SecondKillMapper;
import com.chixing.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class FoodServiceImpl implements IFoodService {
    @Autowired
    private FoodMapper foodMapper;//持久层依赖导入
    @Autowired
    private SecondKillMapper secondKillMapper;//持久层依赖导入

    private QueryWrapper<Food> wrapper = new QueryWrapper<>();

    @Override
    public Food getById(Integer foodId) {
        return foodMapper.selectById(foodId);
    }

    @Override
    public int save(Food food) {
        return foodMapper.insert(food);
    }

    @Override
    public int update(Food food) {
        return foodMapper.updateById(food);
    }

    @Override
    public int remove(Integer foodId) {
        return foodMapper.deleteById(foodId);
    }

    @Override
    public List<Food> getByPage(Integer pageNum) {
        Page<Food> page = new Page<>(pageNum,3);
        return foodMapper.selectPage(page,null).getRecords();
    }

    @Override
    public List<Food> getByPrice() {
        wrapper.clear();
        wrapper.orderByDesc("food_price");
        return foodMapper.selectList(wrapper);
    }

    @Override
    public List<Food> getByScore() {
        wrapper.clear();
        wrapper.orderByDesc("food_score");
        return foodMapper.selectList(wrapper);
    }

    @Override
    public Map<SecondKill,Food> getSKPro() {
        Map<SecondKill,Food> map = new HashMap<>();
        wrapper.clear();
        Page<SecondKill> page = new Page<>(1,4);
        List<SecondKill> secondKills = secondKillMapper.selectPage(page,null).getRecords();
        if (secondKills.size()>0){
            for (SecondKill secondKill : secondKills){
                Integer foodId = secondKill.getFoodId();
                Food food = foodMapper.selectById(foodId);
                map.put(secondKill,food);
            }
            return map;
        }
        return null;
    }
}
