package com.chixing.service.impl;

import com.chixing.entity.Shop;
import com.chixing.mapper.ShopMapper;
import com.chixing.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
public class ShopServiceImpl implements IShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Override
    public Shop getById(int shopId) {
        return shopMapper.selectById(shopId);
    }
}
