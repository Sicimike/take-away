package com.sicimike.user.controller;

import com.sicimike.user.constant.SystemConstant;
import com.sicimike.user.entity.UserInfo;
import com.sicimike.user.enums.EnumResult;
import com.sicimike.user.enums.EnumRole;
import com.sicimike.user.service.UserService;
import com.sicimike.user.util.CookieUtil;
import com.sicimike.user.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author sicimike
 * @create 2018-12-05 11:41
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/login/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        //1. openid和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenId(openid);
        if (userInfo == null) {
            return ResultVO.error(EnumResult.LOGIN_FAIL);
        }

        //2. 判断角色
        if (!EnumRole.BUYER.getCode().equals(userInfo.getRole())) {
            return ResultVO.error(EnumResult.ROLE_ERROR);
        }

        //3. cookie里设置openid=abc
        CookieUtil.set(response, SystemConstant.COOKIE_OPENID, openid, SystemConstant.COOKIE_EXPIRE);

        return ResultVO.success();
    }

    @GetMapping("/login/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        //判断是否已登录
        Cookie cookie = CookieUtil.get(request, SystemConstant.COOKIE_TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(SystemConstant.REDIS_TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVO.success();
        }

        //1. openid和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenId(openid);
        if (userInfo == null) {
            return ResultVO.error(EnumResult.LOGIN_FAIL);
        }

        //2. 判断角色
        if (!EnumRole.SELLER.getCode().equals(userInfo.getRole())) {
            return ResultVO.error(EnumResult.ROLE_ERROR);
        }

        //3. redis设置key=UUID, value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = SystemConstant.COOKIE_EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(SystemConstant.REDIS_TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

        //4. cookie里设置token=UUID
        CookieUtil.set(response, SystemConstant.COOKIE_TOKEN, token, SystemConstant.COOKIE_EXPIRE);

        return ResultVO.success();
    }

}
