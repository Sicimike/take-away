package com.sicimike.order.enums;

import lombok.Getter;

/**
 * @Author sicimike
 */
@Getter
public enum EnumOrderStatus {
    //新订单
    NEW(0, "新订单"),
    //完结
    FINISHED(1, "完结"),
    //取消
    CANCEL(2, "取消");

    private Integer code;
    private String message;

    EnumOrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
