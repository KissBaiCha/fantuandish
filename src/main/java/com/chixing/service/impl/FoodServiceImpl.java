package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Food;
import com.chixing.mapper.FoodMapper;
import com.chixing.service.IFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private FoodMapper foodMapper;//持久层依赖导入
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
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("food_price");
        return foodMapper.selectList(wrapper);
    }

    @Override
    public List<Food> getByScore() {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("food_score");
        return foodMapper.selectList(wrapper);
    }
}
