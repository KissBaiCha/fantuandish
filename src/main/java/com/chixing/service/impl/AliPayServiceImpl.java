package com.chixing.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chixing.entity.Flow;
import com.chixing.entity.Food;
import com.chixing.entity.MyOrder;
import com.chixing.mapper.FlowMapper;
import com.chixing.mapper.FoodMapper;
import com.chixing.mapper.MyOrderMapper;

import com.chixing.service.IAliPayService;
import com.chixing.config.AlipayConfig;
import com.chixing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.chixing.config.AlipayConfig.return_url;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/9/23 18:25
 */
@Service
public class AliPayServiceImpl implements IAliPayService {
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private MyOrderMapper myOrderMapper;
    @Autowired
    private FoodMapper foodMapper;
    @Override
    public String goAliPay(String orderId) {
        MyOrder myOrder = myOrderMapper.selectById(orderId);
        String orderAmount = myOrder.getOrderPrice().toString();
        Food food = foodMapper.selectById(myOrder.getFoodId());
        String name = food.getFoodName();
        //获得初始化的AlipayClient
        AlipayClient alipayClient =  new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl( "http://localhost:8080/fan/alipayReturnNotice" );
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl( "" );
        String timeout_express = "1c";
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\"" + orderId + "\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":"+orderAmount+","  +
                "    \"subject\":\"" + name + "\","  +
                "    \"body\":\""+ name +"\","  +
                "    \"timeout_express\":\""+ timeout_express +"\"}");
        String form= "" ;
        try  {
            //调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    @Transactional
    public ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) {
        System.out.println("支付宝同步回调中");
        ModelAndView modelAndView = new ModelAndView();
        String out_trade_no;
        String trade_no;
        String total_amount;
        try {
            //支付宝交易号
            trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            //订单号
            out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("return out_trade_no:" + out_trade_no);
        System.out.println("return trade_no:" + trade_no);
        System.out.println("return total_amount:" + total_amount);
        // 回调页面
        modelAndView.setViewName(return_url);
        MyOrder order = myOrderMapper.selectById(out_trade_no);
        Food food = foodMapper.selectById(order.getFoodId());
        order.setOrderStatus(2);
        order.setOrderUpdateTime(LocalDateTime.now());
        UpdateWrapper<MyOrder> myOrderUpdateWrapper = new UpdateWrapper<>();
        myOrderUpdateWrapper.eq("order_id",out_trade_no);
        myOrderMapper.update(order,myOrderUpdateWrapper);
        String foodName = food.getFoodName();
        modelAndView.addObject("cusName", JwtUtil.getCusNameBySession(request));
        modelAndView.addObject("productName",foodName);
        modelAndView.addObject("trade_no",trade_no);
        modelAndView.addObject("total_amount",total_amount);
        modelAndView.addObject("out_trade_no",out_trade_no);
        Flow flow = new Flow();
        flow.setOrderId(out_trade_no);
        flow.setFlowNumber(trade_no.trim());
        flow.setFlowPrice(BigDecimal.valueOf(Double.valueOf(total_amount)));
        flow.setFlowCteateTime(LocalDateTime.now());
        flow.setFlowUpdateTime(LocalDateTime.now());
        flow.setPayTime(LocalDateTime.now());
        flow.setFlowStatus(1);
        flowMapper.insert(flow);
        return modelAndView;
    }

    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response){
        System.err.println("支付宝异步回调...");
        return "";
    }
}
