package com.chixing.service;

import com.chixing.entity.Food;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
