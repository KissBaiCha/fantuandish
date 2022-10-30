package com.chixing.commons.pluginsDelay;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/9 14:16
 */
@Configuration
public class PluginsDelayConfig {
    @Bean
    public CustomExchange newDelayExchange(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        return new CustomExchange("delayed-exchange","x-delayed-message",true,false,map);
    }
    @Bean
    public Queue newDelayQueue(){
        return new Queue("delayed-queue",true);
    }
    @Bean
    public Binding bindingDelayedQueue(){
        return BindingBuilder.bind(newDelayQueue()).to(newDelayExchange()).with("zhangXu").noargs();
    }
}
