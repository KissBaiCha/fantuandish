package com.chixing.mapper;

import com.chixing.entity.Food;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
//@Repository//告诉bean容器 这是mapper
public interface FoodMapper extends BaseMapper<Food> {

}
