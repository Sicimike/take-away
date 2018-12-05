package com.sicimike.user.service.impl;

import com.sicimike.user.entity.UserInfo;
import com.sicimike.user.repository.UserRepository;
import com.sicimike.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sicimike
 * @create 2018-12-05 11:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo findByOpenId(String openId) {
        return userRepository.findByOpenid(openId);
    }
}
