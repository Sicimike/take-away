package com.sicimike.server.enums;

import lombok.Getter;

/**
 * @Author sicimike
 * 商品上下架状态
 */
@Getter
public enum EnumProductStatus {

    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;

    private String message;

    EnumProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
