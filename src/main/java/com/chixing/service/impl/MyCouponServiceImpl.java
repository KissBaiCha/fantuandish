package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.Coupon;
import com.chixing.entity.MyCoupon;
import com.chixing.entity.vo.MyCouponVO;
import com.chixing.mapper.CouponMapper;
import com.chixing.mapper.MyCouponMapper;
import com.chixing.service.IMyCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
public class MyCouponServiceImpl implements IMyCouponService {

    @Autowired
    private MyCouponMapper myCouponMapper;
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public Integer save(MyCoupon myCoupon) {
        return myCouponMapper.insert(myCoupon);
    }

    @Override
    public Integer update(MyCoupon myCoupon) {
        return myCouponMapper.updateById(myCoupon);
    }

    @Override
    public MyCoupon getById(Integer myCouponId) {
        return myCouponMapper.selectById(myCouponId);
    }

    @Override
    public BigDecimal getCouponPriceByMyCouponId(Integer myCouponId) {
        Integer couponId = myCouponMapper.selectById(myCouponId).getCouponId();
        return couponMapper.selectById(couponId).getCouponPrice();
    }

    @Override
    public List<MyCoupon> getByPage(Integer pageNum) {
        Page<MyCoupon> page = new Page<>(pageNum,3);
        return myCouponMapper.selectPage(page,null).getRecords();
    }

    @Override
    public List<MyCouponVO> getMyCouponByShopId(Integer customerId,Integer shopId) {
        //查询出当前店铺发布的优惠券
        QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<>();
        couponQueryWrapper.eq("shop_id",shopId);
        List<Coupon> coupons = couponMapper.selectList(couponQueryWrapper);
        if(coupons.size() == 0){
            return null;
        }
        List<Integer> couponIdList = coupons.stream().map(Coupon::getCouponId).collect(Collectors.toList());
        //查询用户所有优惠券
        QueryWrapper<MyCoupon> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id",customerId);
            //添加查询条件: 未使用的优惠券
        wrapper.eq("my_coupon_status",1);
        //添加查询条件: 属于当前店铺的优惠券
        wrapper.in("coupon_id",couponIdList);
        //添加查询条件: 未过期的优惠券
        wrapper.gt("my_coupon_lose_time",LocalDateTime.now());
        List<MyCoupon> myCoupons = myCouponMapper.selectList(wrapper);
        List<MyCouponVO> myCouponVOList = new ArrayList<>();
        for (MyCoupon myCoupon : myCoupons) {
            Coupon coupon = couponMapper.selectById(myCoupon.getCouponId());
            MyCouponVO myCouponVO = new MyCouponVO(myCoupon
                    ,coupon.getCouponPrice()
                    ,coupon.getCouponCondition());
            myCouponVOList.add(myCouponVO);
        }
        return myCouponVOList;
    }

    @Override
    public List<MyCoupon> getAvailableMyCoupon(Integer cusId) {
        QueryWrapper<MyCoupon> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id",cusId);
        wrapper.eq("my_coupon_status",1);
        return myCouponMapper.selectList(wrapper);
    }

    @Override
    public List<MyCoupon> getNotAvailableMyCoupon(Integer cusId) {
        QueryWrapper<MyCoupon> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id",cusId);
        wrapper.eq("my_coupon_status",0);
        return myCouponMapper.selectList(wrapper);
    }

    @Override
    public Page<MyCoupon> getByPage(Integer pageNum, Integer cusId, Integer status) {
        Page<MyCoupon> page = new Page<>(pageNum,5);
        QueryWrapper<MyCoupon>myCouponQueryWrapper = new QueryWrapper<>();
        myCouponQueryWrapper.eq("customer_id",cusId);
        myCouponQueryWrapper.eq("my_coupon_status",status);
        return myCouponMapper.selectPage(page,myCouponQueryWrapper);
    }




}
