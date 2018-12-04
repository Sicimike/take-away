package com.sicimike.order.message;

import com.sicimike.order.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 发送MQ消息测试
 * @author sicimike
 * @create 2018-12-03 17:54
 */
@Component
public class MqReceiverTests extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testRabbitMQ(){
        amqpTemplate.convertAndSend("myQueue", "now is " + new Date());
    }
}
