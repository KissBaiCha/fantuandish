package com.chixing.service;

import com.chixing.commons.R;
import com.chixing.entity.Food;
import com.chixing.entity.FoodCollection;
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
public interface IFoodCollectionService  {
    /**
     * 添加收藏记录方法
     * @param cusId 用户ID
     * @param foodId 美食ID
     * @return 返回R.message 成功 or 失败
     */
    R<String> saveByCusId(Integer cusId,Integer foodId);

    /**
     * 查询用户收藏美食方法
     * @param cusId 用户ID
     * @return 收藏的所有美食
     */
    R<List<Food>> getAllFoodColByCusId(Integer cusId);

    /**
     * 删除收藏美食
     * @param foodCollectionId 美食收藏ID
     * @return R
     */
    R<String> removeById(Integer foodCollectionId);
}
