package com.chixing.service;

import com.chixing.entity.EvaImg;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface IEvaImgService{
    void save(Integer evaId,String[] imgArr);
}
