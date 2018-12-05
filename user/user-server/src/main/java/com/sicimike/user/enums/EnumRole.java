package com.sicimike.user.enums;

import lombok.Getter;

/**
 * @author sicimike
 * @create 2018-12-05 13:53
 */
@Getter
public enum EnumRole {

    //买家
    BUYER(1, "买家"),
    //卖家
    SELLER(2, "卖家");

    private Integer code;

    private String message;

    EnumRole(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
