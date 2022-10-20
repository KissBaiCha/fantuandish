package com.chixing.mapper;

import com.chixing.entity.Food;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Mapper
public interface FoodMapper extends BaseMapper<Food> {

}
