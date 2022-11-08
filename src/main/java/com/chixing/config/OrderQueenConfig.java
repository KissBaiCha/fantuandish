package com.chixing.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xu Zhang
 * @date 2022/10/31
 */
@Configuration
public class OrderQueenConfig {
    @Bean
    public Queue orderDelayQueue() {
        //String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments
        //名字、        是否持久化、         是否排他、           是否自动删除、             自定义属性....
        Map<String,Object> arguments = new HashMap<>(2);
        //死信路由
        arguments.put("x-dead-letter-exchange","order-exchange");
        //死信路由键
        arguments.put("x-dead-letter-routing-key","order-release");
        //消息过期时间、单位：毫秒、60000代表1分钟
        arguments.put("x-message-ttl",60000 * 15);
        return new Queue("order-delay-queue",true,false,false,arguments);
    }
    @Bean
    public Queue orderReleaseOrderQueue() {
        return new Queue("order-release-queue",true,false,false);
    }
    @Bean
    public Exchange orderExchange() {
        return new TopicExchange("order-exchange",true,false);
    }
    @Bean
    public Binding orderCreate() {
        //String destination, Binding.DestinationType destinationType, String exchange, String routingKey, @Nullable Map<String, Object> arguments
        //目的地、目的地类型、交换机、路由键
        return new Binding("order-delay-queue",Binding.DestinationType.QUEUE,"order-exchange","order-create",null);
    }
    @Bean
    public Binding orderRelease() {
        return new Binding("order-release-queue", Binding.DestinationType.QUEUE,"order-exchange","order-release",null);
    }
}
