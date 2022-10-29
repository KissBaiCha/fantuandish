package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.entity.ShopImg;
import com.chixing.mapper.ShopImgMapper;
import com.chixing.service.IShopImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
public class ShopImgServiceImpl implements IShopImgService {

    @Autowired
    private ShopImgMapper shopImgMapper;
    @Override
    public List<ShopImg> getShopSrc(Integer shopId) {
        QueryWrapper<ShopImg> wrapper = new QueryWrapper<>();
        wrapper.eq("shop_id",shopId);
        wrapper.eq("img_status",0);
        List<ShopImg> shopImgs = shopImgMapper.selectList(wrapper);
        return shopImgs;
    }
}
