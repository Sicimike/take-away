package com.sicimike.user.constant;

/**
 * 系统常量
 * @author sicimike
 * @create 2018-12-05 14:24
 */
public interface SystemConstant {

    String COOKIE_TOKEN = "token";
    String COOKIE_OPENID = "openid";
    /**
     * 过期时间(单位:s)
     */
    Integer COOKIE_EXPIRE = 7200;

    String REDIS_TOKEN_TEMPLATE = "token_%s";

}
