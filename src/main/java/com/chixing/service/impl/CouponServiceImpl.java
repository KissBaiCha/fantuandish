package com.chixing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Coupon;
import com.chixing.mapper.CouponMapper;
import com.chixing.service.ICouponService;
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
public class CouponServiceImpl implements ICouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public int save(Coupon coupon) {
        return couponMapper.insert(coupon);
    }

    @Override
    public int remove(int couponId) {
        return couponMapper.deleteById(couponId);
    }

    @Override
    public int update(Coupon coupon) {
        return couponMapper.updateById(coupon);
    }

    @Override
    public Coupon getById(int couponId) {
        return couponMapper.selectById(couponId);
    }

    @Override
    public List<Coupon> getByPage(Integer pageNum) {
        Page<Coupon> page = new Page<>(pageNum,3);
        return couponMapper.selectPage(page,null).getRecords();
    }
}
