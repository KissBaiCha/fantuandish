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

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static com.chixing.commons.ResultCodeEnum.*;


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
    public R<String> loginByCode(Long telno,Integer code,Integer sessionCode) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_telno",telno);
        if (telno == null)
            return R.fail(TEL_NULL);
        if(customerMapper.selectCount(queryWrapper) == 0){
            return R.fail(NO_FIND_ANT_ERR);
        }
//        System.out.println(sessionCode);
//        System.out.println(code);
        if(sessionCode.equals(code)){
            Customer customer = customerMapper.selectOne(queryWrapper);
            return loginByName(customer);
        }
        return R.fail(CODE_ERR);
    }

    @Override
    public R<String> checkTel(Long telno) {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("customer_telno",telno);
        if (telno == null)
            return R.fail(TEL_NULL);
        if(customerMapper.selectCount(queryWrapper) == 0)
            return R.fail(NO_FIND_ANT_ERR);
        return R.fail(SEND_SUCCESS);
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
    public String registerUser(Customer customer,Integer code,Integer verCode) {
        Customer hasCustomer =getCustomerByName(customer.getCustomerName());
        if (code!=null){
            if (code.equals(verCode)) {
                if (hasCustomer == null) {
                    Customer hasTelno = getCustomerByTel(customer.getCustomerTelno());
                    if (hasTelno == null) {
                        customer.setCustomerStatus(1);
                        customer.setCustomerHeadImg("https://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/user/tb12b543f1a.png");
                        customer.setCustomerCreateDate(LocalDate.now());
                        customerMapper.insert(customer);
                        return "注册成功！";
                    } else
                        return "该号码已经注册，请更换手机号码！";
                }
                return "该用户名已存在，请重新注册！";
            } else
                return "验证码错误";
        }else
            return "请获取验证码";

    }
}
