package com.sicimike.user.enums;

import lombok.Getter;

/**
 * @author sicimike
 * @create 2018-12-05 13:49
 */
@Getter
public enum EnumResult {

    //登录失败
    LOGIN_FAIL(1, "登录失败"),
    //权限有误
    ROLE_ERROR(2, "角色权限有误");

    private Integer code;

    private String message;

    EnumResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
