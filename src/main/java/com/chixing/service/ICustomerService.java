package com.chixing.service;

import com.chixing.commons.R;
import com.chixing.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
public interface ICustomerService {
    /**
     * 登录
     * @param customer 用户信息封装
     * @return 返回前端数据封装
     */
    R<String> loginByName(Customer customer);
    R<String> loginByCode(Customer customer,Integer code);


    /**
     * 根据id查询用户信息
     * @param customerId
     * @return
     */
    Customer getCustomerById(Integer customerId);
    Customer getCustomerByName(String customerName);
    Customer getCustomerByTel(Long telno);
    int registerUser(Customer customer);
}
