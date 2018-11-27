package com.sicimike.server.exception;

import com.sicimike.server.enums.EnumResult;

public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(EnumResult enumResult) {
        super(enumResult.getMessage());
        this.code = enumResult.getCode();
    }
}
