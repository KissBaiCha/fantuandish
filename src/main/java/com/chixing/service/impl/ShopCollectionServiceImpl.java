package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.commons.IGlobalCache;
import com.chixing.entity.Shop;
import com.chixing.entity.ShopCollection;
import com.chixing.mapper.ShopCollectionMapper;
import com.chixing.mapper.ShopMapper;
import com.chixing.service.IShopCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class ShopCollectionServiceImpl implements IShopCollectionService {

    @Autowired
    private ShopCollectionMapper shopCollectionMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private IGlobalCache iGlobalCache;

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


@Override
    public void save(Integer shopId, Integer userId) {
        String key = "scShop:shopId_"+shopId+"userId_"+userId;
        ShopCollection shopCollection = new ShopCollection();
            shopCollection.setShopId(shopId);
            shopCollection.setCustomerId(userId);
            shopCollection.setShopCollectionTime(LocalDateTime.now());
            shopCollection.setShopCollectionCteateTime(LocalDateTime.now());
            iGlobalCache.set(key,shopCollection,60*10);
    }
    @Override
    public void delete(Integer shopId, Integer userId) {
        String key = "scShop:shopId_"+shopId+"userId_"+userId;
        if (iGlobalCache.hasKey(key))
            iGlobalCache.del(key);
        else
            shopCollectionMapper.delete(getConllection(shopId,userId));

    }
    @Override
    public boolean userToCollection(Integer shopId,Integer userId) {
        String key = "scShop:shopId_"+shopId+"userId_"+userId;
        if (iGlobalCache.hasKey(key))
            return true;
        else
            return shopCollectionMapper.selectCount(getConllection(shopId,userId))==1;
    }

    /**
     * 每隔五分钟将Redis数据持久化到数据库
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void chijiuhua() {
        String key = "scShop:*";
        if (iGlobalCache.getKeys(key).size() > 0) {
            Set<String> keys = iGlobalCache.getKeys(key);
            List<ShopCollection> shopCollectionList = iGlobalCache.getRedisTemplate().opsForValue().multiGet(keys);
            for (ShopCollection shopCollection : shopCollectionList){
                if (shopCollectionMapper.selectCount(getConllection(shopCollection.getShopId(),shopCollection.getCustomerId()))==0)
                    shopCollectionMapper.insert(shopCollection);
                iGlobalCache.del("scShop:shopId_"+shopCollection.getShopId()+"userId_"+shopCollection.getCustomerId());
            }
        }

    }
}
