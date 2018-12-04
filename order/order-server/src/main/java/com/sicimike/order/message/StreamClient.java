package com.sicimike.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author sicimike
 * @create 2018-12-03 19:33
 */
public interface StreamClient {

    String INPUT = "input";

    String OUTPUT = "output";

    /**
     * @return
     */
    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    /**
     * @return
     */
    @Output(StreamClient.OUTPUT)
    MessageChannel output();
}
