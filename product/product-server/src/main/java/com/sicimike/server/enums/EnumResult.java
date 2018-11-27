package com.sicimike.server.enums;

import lombok.Getter;

/**
 * @Author sicimike
 */
@Getter
public enum EnumResult {
    //商品不存在
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    //库存有误
    PRODUCT_STOCK_ERROR(2, "库存有误");

    private Integer code;
    private String message;

    EnumResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
