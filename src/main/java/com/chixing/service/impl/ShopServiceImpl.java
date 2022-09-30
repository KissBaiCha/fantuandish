package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.Shop;
import com.chixing.mapper.ShopMapper;
import com.chixing.service.IShopService;
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
public class ShopServiceImpl implements IShopService {
    @Resource
    private ShopMapper shopMapper;
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
    public List<Shop> getBySift(String foodType, Float shopAvgCost) {
        QueryWrapper<Shop> wrapper = new QueryWrapper<>();
        //筛选业务
        List<Shop> shopList = shopMapper.selectList(wrapper);
        return null;
    }


}
