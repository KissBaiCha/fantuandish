package com.chixing.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Shop;



/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 * 查 get  增 save  改 update   删 remove
 */
public interface IShopService{
    Shop getById(Integer shopId);
    int save(Shop shop);
    int update(Shop shop);
    int remove(Integer shopId);
    //默认分页查询
    Page<Shop> getByPage(Integer pageNum);
    //多条件筛选

    //整合
    Page<Shop> getBySift(Integer pageNum,String foodType,String foodPrice,String foodSort);
}
