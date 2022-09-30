package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chixing.entity.MyCoupon;
import com.chixing.mapper.MyCouponMapper;
import com.chixing.service.IMyCouponService;
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
public class MyCouponServiceImpl implements IMyCouponService {

    @Autowired
    private MyCouponMapper myCouponMapper;

    @Override
    public int save(MyCoupon myCoupon) {
        return myCouponMapper.insert(myCoupon);
    }

    @Override
    public int remove(int myCouponId) {
        return myCouponMapper.deleteById(myCouponId);
    }

    @Override
    public int update(MyCoupon myCoupon) {
        return myCouponMapper.updateById(myCoupon);
    }

    @Override
    public MyCoupon getById(int myCouponId) {
        return myCouponMapper.selectById(myCouponId);
    }

    @Override
    public List<MyCoupon> getByPage(Integer pageNum) {
        Page<MyCoupon> page = new Page<>(pageNum,3);
        return myCouponMapper.selectPage(page,null).getRecords();
    }

    @Override
    public List<MyCoupon> getMyCoupon(int customerId) {
        QueryWrapper<MyCoupon> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id",customerId);
        return myCouponMapper.selectList(wrapper);
    }
}
