package com.chixing.service;

import com.chixing.entity.ShopImg;
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
public interface IShopImgService  {
    List<ShopImg> getShopSrc(Integer shopId);
}
