package com.chixing.commons.pluginsDelay;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/9 14:24
 */
@RestController
public class Product {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @PostMapping("delaye")
    public String sendMsg(){
        String msg = "萝卜呼叫土豆" + new Date();
        rabbitTemplate.convertAndSend("delayed-exchange","zhangXu",msg,message ->{
            message.getMessageProperties().setDelay(5000);
            return message;
        });
        return "everything is ok";
    }

}
