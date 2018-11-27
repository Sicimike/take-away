package com.sicimike.order.enums;

import lombok.Getter;

/**
 * @Author sicimike
 */
@Getter
public enum EnumPayStatus {
    //等待支付
    WAIT(0, "等待支付"),
    //支付成功
    SUCCESS(1, "支付成功");

    private Integer code;
    private String message;

    EnumPayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
