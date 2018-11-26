package com.sicimike.product.exception;

import com.sicimike.product.enums.EnumResult;

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
