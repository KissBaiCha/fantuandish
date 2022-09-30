package com.chixing.service.impl;

import com.chixing.entity.Food;
import com.chixing.mapper.FoodMapper;
import com.chixing.service.IFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    private FoodMapper mapper;//持久层依赖导入
    @Override
    public Food getById(Integer foodId) {
        return mapper.selectById(foodId);
    }

    @Override
    public int save(Food food) {
        return mapper.insert(food);
    }

    @Override
    public int update(Food food) {
        return mapper.updateById(food);
    }

    @Override
    public int remove(Integer foodId) {
        return mapper.deleteById(foodId);
    }
}
