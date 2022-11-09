package com.chixing.util;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/11 13:55
 */
@Component
@Slf4j
@SuppressWarnings("all")
public class TokenInterceptor implements HandlerInterceptor {
    private static final String REQUEST_METHOD = "OPTIONS";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(REQUEST_METHOD.equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = (String) request.getSession().getAttribute("token");
        log.info("拦截器获得的token:"+token);
        if (token!=null){
            if (JwtUtil.verifyToken(token)){
                System.out.println("通过拦截器");
                return true;
            }
        }
        try {
            JSONObject json = new JSONObject();
            json.put("msg","用户信息过期请重新登陆");
            json.put("code","500");
            response.getWriter().append(json.toString());
            log.info("认证失败，未通过拦截器");
            response.sendRedirect(request.getContextPath()+"/user/login.html");
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
