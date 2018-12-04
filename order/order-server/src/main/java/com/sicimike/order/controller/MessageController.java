package com.sicimike.order.controller;

import com.sicimike.order.dto.OrderDTO;
import com.sicimike.order.message.StreamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sicimike
 * @create 2018-12-03 20:26
 */
@Slf4j
@RestController
public class MessageController {

    @Autowired
    private StreamClient streamClient;

    /**
     * 发送 orderDTO对象
     */
    @GetMapping("/sendMessage")
    public void process() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }

}
