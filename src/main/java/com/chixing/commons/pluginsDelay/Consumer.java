package com.chixing.commons.pluginsDelay;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/9 14:28
 */
@Component
public class Consumer {
    @RabbitListener(queues = "delayed-queue")
    public void getMsg(Message message, Channel channel){
        System.err.println("土豆收到：" + message);
        System.err.println(new Date());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
