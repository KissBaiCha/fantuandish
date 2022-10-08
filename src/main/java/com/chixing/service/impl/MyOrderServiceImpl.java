package com.chixing.service.impl;

import com.chixing.entity.MyOrder;
import com.chixing.mapper.MyOrderMapper;

import com.chixing.service.IMyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kaka
 * @since 2022-10-02
 */
@Service
public class MyOrderServiceImpl implements IMyOrderService {

    @Autowired
    private MyOrderMapper myOrderMapper;

    @Override
    public MyOrder getById(String orderId) {
        MyOrder myOrder = myOrderMapper.selectById(orderId);
        System.out.println("myOrder:" + myOrder);
        return myOrder;
    }

    @Override
    public List<MyOrder> getAll() {
        return myOrderMapper.selectList(null);
    }

    @Override
    public int save(MyOrder myOrder) {
        int rows = myOrderMapper.insert(myOrder);
        return rows;
    }

    @Override
    public boolean update(MyOrder myOrder) {
        return myOrderMapper.updateById(myOrder) > 0;
    }

    @Override
    public boolean remove(String orderId) {
        return myOrderMapper.deleteById(orderId) >0;
    }
}
