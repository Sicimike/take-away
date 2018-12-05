package com.sicimike.user.service;

import com.sicimike.user.entity.UserInfo;

/**
 * @author sicimike
 * @create 2018-12-05 11:38
 */
public interface UserService {

    /**
     * 根据openId查找
     * @param openId
     * @return
     */
    UserInfo findByOpenId(String openId);
}
