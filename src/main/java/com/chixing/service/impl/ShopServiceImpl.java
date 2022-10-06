package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.Food;
import com.chixing.entity.Shop;
import com.chixing.mapper.FoodMapper;
import com.chixing.mapper.ShopMapper;
import com.chixing.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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
    public List<Shop> getByFoodType(String foodType) {
        List<Shop> shopList = shopMapper.selectByFoodType(foodType);
        return shopList;
    }

    @Override
    public List<Shop> getByShopAvgCost(Float shopMinCost,Float shopMaxCost) {
        List<Shop> shopList = shopMapper.selectByShopAvgCost(shopMinCost,shopMaxCost);
        return shopList;
    }


    @Override
    public List<Shop> getBySift(String foodType,Float shopMinCost,Float shopMaxCost){
        List<Shop> shopList = shopMapper.selectBySift(foodType,shopMinCost,shopMaxCost);
        return shopList;
    }


}
