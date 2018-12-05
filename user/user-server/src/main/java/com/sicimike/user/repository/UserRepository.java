package com.sicimike.user.repository;

import com.sicimike.user.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author sicimike
 * @create 2018-12-05 11:36
 */
public interface UserRepository extends CrudRepository<UserInfo, String> {

    /**
     * 根据openId查找
     * @param openId
     * @return
     */
    UserInfo findByOpenid(String openId);
}
