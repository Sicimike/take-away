package com.sicimike.order.message;

import com.sicimike.order.OrderApplicationTests;
import com.sicimike.order.dto.CartDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author sicimike
 * @create 2018-12-03 19:38
 */
@Component
public class StreamReceiverTests extends OrderApplicationTests {

    @Autowired
    private StreamClient streamClient;

    @Test
    public void testSendMessage(){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId("123456");
        cartDTO.setProductQuantity(5);
        streamClient.output().send(MessageBuilder.withPayload(cartDTO).build());
    }
}
