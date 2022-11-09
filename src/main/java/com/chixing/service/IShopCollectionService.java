package com.chixing.service;

import com.chixing.entity.Shop;
import com.chixing.entity.ShopCollection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface IShopCollectionService  {
    Long getTotalCollection(Integer shopId);
    List<Shop> getAllShopByUser(Integer userId);

    void save(Integer shopId,Integer userId);
    void delete(Integer shopId,Integer userId);
    boolean userToCollection(Integer shopId,Integer userId);
}
