package com.chixing.service;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/9/23 18:21
 */
public interface IAliPayService {
    String goAliPay(String orderId);

    ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletRequest response);

    String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response);
}
