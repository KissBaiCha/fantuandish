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

    /**
     * 通过手机号短信验证码登录
     * @param telno
     * @param code
     * @param sessionCode
     * @return
     */
    R<String> loginByCode(Long telno,Integer code,Integer sessionCode);
    //验证手机号码
    R<String> checkTel(Long telno);

    /**
     * 根据id查询用户信息
     * @param customerId
     * @return
     */
    Customer getCustomerById(Integer customerId);
    Customer getCustomerByName(String customerName);
    Customer getCustomerByTel(Long telno);
    //注册
    String registerUser(Customer customer,Integer code,Integer verCode);

}
