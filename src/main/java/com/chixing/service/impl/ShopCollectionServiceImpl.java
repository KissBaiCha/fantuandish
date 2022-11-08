package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.ShopCollection;
import com.chixing.mapper.ShopCollectionMapper;
import com.chixing.service.IShopCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
