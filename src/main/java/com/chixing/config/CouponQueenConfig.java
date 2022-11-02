package com.chixing.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author Xu Zhang
 * @date 2022/11/2
 */
@Configuration
public class CouponQueenConfig {
    @Bean
    public DirectExchange newExchange(){
        return new DirectExchange("coupon-Exchange",true,false);
    }
    @Bean
    public Queue newQueue(){
        return new Queue("coupon-Queue",true);
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(newQueue()).to(newExchange()).with("coupon");
    }
}
