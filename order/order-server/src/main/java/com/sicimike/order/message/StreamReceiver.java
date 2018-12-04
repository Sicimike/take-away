package com.sicimike.order.message;

import com.sicimike.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author sicimike
 * @create 2018-12-03 19:36
 */
@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

//    @StreamListener(StreamClient.INPUT)
//    public void processInput(Object message){
//        log.info("StreamReceiver.processInput {}", message);
//    }
//
    @StreamListener(StreamClient.OUTPUT)
    public void processOutput(OrderDTO message){
        log.info("StreamReceiver.processOutput {}" , message);
    }
}
