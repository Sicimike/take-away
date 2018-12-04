package com.sicimike.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sicimike
 * @create 2018-12-03 17:50
 */
@Slf4j
@Component
public class MqReceiver {

    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void process(String message){
        log.info("receive message : " + message);
    }
}
