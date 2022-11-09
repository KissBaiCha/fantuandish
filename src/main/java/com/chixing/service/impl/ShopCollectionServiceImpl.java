package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.Shop;
import com.chixing.entity.ShopCollection;
import com.chixing.mapper.ShopCollectionMapper;
import com.chixing.mapper.ShopMapper;
import com.chixing.service.IShopCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class ShopCollectionServiceImpl implements IShopCollectionService {

    @Autowired
    private ShopCollectionMapper shopCollectionMapper;
    @Autowired
    private ShopMapper shopMapper;

    private QueryWrapper<ShopCollection> wrapper = new QueryWrapper();
    public QueryWrapper<ShopCollection> getConllection(Integer shopId, Integer userId){
        wrapper.clear();
        wrapper.eq("shop_id",shopId)
               .eq("customer_id",userId);
        return wrapper;
    }
    @Override
    public Long getTotalCollection(Integer shopId) {
        wrapper.clear();
        wrapper.eq("shop_id",shopId);
        return shopCollectionMapper.selectCount(wrapper);
    }

    @Override
    public int save(Integer shopId, Integer userId) {
        ShopCollection shopCollection = new ShopCollection();
        shopCollection.setShopId(shopId);
        shopCollection.setCustomerId(userId);
        shopCollection.setShopCollectionTime(LocalDateTime.now());
        shopCollection.setShopCollectionCteateTime(LocalDateTime.now());
        return shopCollectionMapper.insert(shopCollection);
    }

    @Override
    public int delete(Integer shopId, Integer userId) {
        return shopCollectionMapper.delete(getConllection(shopId,userId));
    }

    @Override
    public boolean userToCollection(Integer shopId,Integer userId) {
        return shopCollectionMapper.selectCount(getConllection(shopId,userId))==1;
    }

    @Override
    public List<Shop> getAllShopByUser(Integer userId) {
        QueryWrapper<ShopCollection> shopCollectionQueryWrapper = new QueryWrapper<>();
        shopCollectionQueryWrapper.eq("customer_id",userId);
        shopCollectionQueryWrapper.select("distinct shop_id");
        List<ShopCollection> shopCollection = shopCollectionMapper.selectList(shopCollectionQueryWrapper);
        List<Integer> shopIdList = new ArrayList<>();
        for (ShopCollection sc : shopCollection){
            shopIdList.add(sc.getShopId());
        }
        QueryWrapper<Shop> shopQueryWrapper = new QueryWrapper<>();
        shopQueryWrapper.in("shop_id",shopIdList);
        return shopMapper.selectList(shopQueryWrapper);
    }
}
