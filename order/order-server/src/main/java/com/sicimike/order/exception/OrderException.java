package com.sicimike.order.exception;

import com.sicimike.order.enums.EnumResult;

/**
 * @Author sicimike
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(EnumResult enumResult) {
        super(enumResult.getMessage());
        this.code = enumResult.getCode();
    }

}
