package com.chixing.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 更改数据库库存队列配置类
 * @author Xu Zhang
 * @date 2022/11/8
 */
@Configuration
public class DecrStockQueueConfig {
    @Bean
    public DirectExchange DecrStockExchange(){
        return new DirectExchange("DecrStockExchange",true,false);
    }
    @Bean
    public Queue DecrStockQueue(){
        return new Queue("DecrStockQueue",true);
    }
    @Bean
    public Binding DecrStockbinding(){
        return BindingBuilder.bind(DecrStockQueue()).to(DecrStockExchange()).with("DecrStock");
    }
}
