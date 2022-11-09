package com.chixing.config.Interceptor;

import com.chixing.util.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/19 18:28
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
//                进行拦截，一般登录不拦截，企业都拦截
                .addPathPatterns("/customer/**")
                .excludePathPatterns("/customer/login")
                .excludePathPatterns("/customer/loginByCode/**")
                .excludePathPatterns("/customer/sendCode/*")
                .excludePathPatterns("/customer/register/*")
        ;
    }
}
