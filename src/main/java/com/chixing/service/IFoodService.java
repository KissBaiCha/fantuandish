package com.chixing.service;

import com.chixing.entity.Food;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chixing.entity.SecondKill;
import com.chixing.entity.Shop;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 * 查 get  增 save  改 update   删 remove
 */
public interface IFoodService  {
     Food getById(Integer foodId);
     int save(Food food);
     int update(Food food);
     int remove(Integer foodId);

     //分页查询
     List<Food> getByPage(Integer pageNum);
     //价格排序
     List<Food> getByPrice();
     //评分排序
     List<Food> getByScore();

     //食品类别
     List<Food> foodTypes();
     //筛选页秒杀美食
     Map<SecondKill,Food> getSKPro();
     //店铺主页秒杀美食
     Map<SecondKill,Food> getSKProById(Integer shopId);
     //店铺主页美食
     List<Food> getShopProByShopId(Integer shopId);
     //店铺推荐美食
     List<Food> getShopProByScore(Integer shopId);
}
