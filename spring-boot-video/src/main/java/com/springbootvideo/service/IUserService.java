package com.springbootvideo.service;

import com.springbootvideo.common.model.JsonResult;
import com.springbootvideo.model.User;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 13:11
 * @Description:
 */
public interface IUserService {

    /**
     * 注册
     * @param user
     * @return
     */
    JsonResult registUser(User user);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    JsonResult loginService(String username,String password);

    /**
     * 初始化VIP会员信息
     */
    void initVip();
}
