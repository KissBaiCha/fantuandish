package com.chixing.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chixing.commons.R;
import com.chixing.commons.ResultCodeEnum;
import com.chixing.entity.Customer;
import com.chixing.entity.vo.CustomerTokenVO;
import com.chixing.mapper.CustomerMapper;
import com.chixing.service.ICustomerService;
import com.chixing.util.JwtUtil;
import com.chixing.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.chixing.commons.ResultCodeEnum.CUSTOMER_ERR;
import static com.chixing.commons.ResultCodeEnum.NO_FIND_ANT_ERR;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-30
 */
@Service
@Slf4j
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public R<String> loginByName(Customer customer) {
        log.info(customer.getCustomerName());
        log.info(customer.getCustomerPwd());
        QueryWrapper<Customer> cusQueryWrapper = new QueryWrapper<>();
        cusQueryWrapper.eq("customer_name",customer.getCustomerName());
        Long hasCount = customerMapper.selectCount(cusQueryWrapper);
        if(hasCount == 1){
            cusQueryWrapper.eq("customer_pwd",customer.getCustomerPwd());
            Customer newCustomer = customerMapper.selectOne(cusQueryWrapper);
            if(newCustomer != null) {
                //2. JWT创建token
                String token = JwtUtil.createToken(new CustomerTokenVO(
                        newCustomer.getCustomerId(),
                        newCustomer.getCustomerName(),
                        newCustomer.getCustomerTelno(),
                        newCustomer.getCustomerCreateDate(),
                        newCustomer.getCustomerHeadImg()));
                R<String> result = R.ok("token",token);
                log.info("login获得的token是" + result.getData().get("token"));
                return result;
            }
            return R.fail(CUSTOMER_ERR);
        }else{
            return R.fail(NO_FIND_ANT_ERR);
        }

    }

    @Override
    public R<String> loginByCode(Customer customer,Integer code) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_telno",customer.getCustomerTelno());
        if(customerMapper.selectCount(queryWrapper) == 0){
            return R.fail(NO_FIND_ANT_ERR);
        }
        R<Integer> integerR = SmsUtil.sendMsg();
        if(integerR.getData().get("code").equals(code)){

        }
        return null;
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        //查询用户是否存在
        Customer customer = customerMapper.selectById(customerId);
        if (customer == null){
            System.out.println("数据为空"); //不存在
        }else {
            //存在 要传给前端的数据
            Customer customer1 = new Customer();
            customer1.setCustomerName(customer.getCustomerName());
            customer1.setCustomerPwd(customer.getCustomerPwd());
            customer1.setCustomerTelno(customer.getCustomerTelno());
        }

        return customer;
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_name",customerName);
        Customer customer = customerMapper.selectOne(wrapper);
        return customer;
    }

    @Override
    public Customer getCustomerByTel(Long telno) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_telno",telno);
        Customer customer = customerMapper.selectOne(wrapper);
        return customer;
    }

    @Override
    public int registerUser(Customer customer) {
        customer.setCustomerStatus(1);
        customer.setCustomerCreateDate(LocalDate.now());
        return customerMapper.insert(customer);
    }
}
