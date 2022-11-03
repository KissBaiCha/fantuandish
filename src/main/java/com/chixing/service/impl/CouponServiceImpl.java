package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Coupon;
import com.chixing.entity.MyCoupon;
import com.chixing.mapper.CouponMapper;
import com.chixing.mapper.MyCouponMapper;
import com.chixing.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Autowired
    private MyCouponMapper myCouponMapper;

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
    public List<Coupon> getByShopId(Integer shopId) {
        QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<>();
        couponQueryWrapper.eq("shop_id",shopId);
        return couponMapper.selectList(couponQueryWrapper);
    }

    @Override
    public boolean saveByCusId(Integer couponId, Integer cusId) {
        if(isHasCoupon(couponId,cusId)){
            MyCoupon myCoupon = new MyCoupon();
            myCoupon.setCustomerId(cusId);
            myCoupon.setCouponId(couponId);
            myCoupon.setMyCouponGetTime(LocalDateTime.now());
            myCoupon.setMyCouponLoseTime(LocalDateTime.now().plusDays(couponMapper.selectById(couponId).getCouponValidDays()));
            myCoupon.setMyCouponStatus(1);
            myCoupon.setMyCouponCteateTime(LocalDateTime.now());
            myCoupon.setMyCouponUpdateTime(LocalDateTime.now());
            myCouponMapper.insert(myCoupon);
            return true;
        }
        return false;
    }
    @Override
    public boolean isHasCoupon(Integer couponId, Integer cusId){
        QueryWrapper<MyCoupon> myCouponQueryWrapper = new QueryWrapper<>();
        myCouponQueryWrapper.eq("coupon_id",couponId)
                            .eq("customer_id",cusId);
        return myCouponMapper.selectCount(myCouponQueryWrapper) == 0;
    }
}
