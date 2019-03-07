package com.springbootvideo.service.impl;

import com.springbootvideo.common.constant.VideoConstant;
import com.springbootvideo.common.model.JsonResult;
import com.springbootvideo.common.service.RedisService;
import com.springbootvideo.common.util.DateUtil;
import com.springbootvideo.common.util.MD5;
import com.springbootvideo.common.util.UUIDUtil;
import com.springbootvideo.dao.UserDao;
import com.springbootvideo.model.User;
import com.springbootvideo.service.IMailService;
import com.springbootvideo.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 13:12
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private RedisService redisService;
    @Resource
    private UserDao userDao;
    @Resource
    private IMailService mailService;

    private MD5 md5 = new MD5();

    @Override
    public JsonResult registUser(User user) {
        JsonResult jsonResult = new JsonResult();
        HashMap<String,String> map = new HashMap<String,String>();
        try {
            map.put("email",user.getEmail());
            User one = userDao.findOne(map);
            if(null == one){
                user.setSalt(UUIDUtil.getUUID());
                user.setPassword(md5.encryString(user.getPassword(),user.getSalt()));
                userDao.save(user);
                jsonResult.setSuccess(true);
                jsonResult.setMsg(VideoConstant.REGIST_SUCCESS);

                mailService.sendEmail(user.getEmail());
            } else {
                jsonResult.setSuccess(true);
                jsonResult.setMsg(VideoConstant.USER_EXIST);
            }
        } catch (Exception e){
            jsonResult.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    @Override
    public JsonResult loginService(String username, String password) {
        JsonResult jsonResult = new JsonResult();
        HashMap<String,String> map = new HashMap<String,String>();
        try {
            map.put("email",username);
            User one = userDao.findOne(map);
            if(null == one){
                jsonResult.setMsg(VideoConstant.USER_NOT_EXIST);
                return jsonResult;
            }
            String password_salt = md5.encryString(password, one.getSalt());
            if(!one.getPassword().equals(password_salt)){
                jsonResult.setMsg(VideoConstant.PASSWORD_ERROR);
                return jsonResult;
            }
            //判断是否是vip
            String isVip = redisService.get(VideoConstant.VIP_USER + ":" + one.getEmail());
            if(!StringUtils.isEmpty(isVip)){
                one.setIcon(VideoConstant.ICON_VIP_Y);
                one.setTitle(VideoConstant.ICON_VIP_Y_TITLE);
            } else {
                one.setIcon(VideoConstant.ICON_VIP_N);
                one.setTitle(VideoConstant.ICON_VIP_N_TITLE);
            }
            jsonResult.setSuccess(true);
            jsonResult.setMsg(VideoConstant.LOGIN_SUCCESS);
            jsonResult.setObj(one);
        } catch (Exception e){
            jsonResult.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    @Override
    public void initVip() {
        HashMap<String,String> map = new HashMap<String,String>();
        try {
            map.put("isVip","1");
            List<User> vipUsers = userDao.findByCond(map);
            vipUsers.forEach( u -> {
                int second = DateUtil.compareDateSecond(u.getVipExpirTime(), new Date());
                redisService.save(VideoConstant.VIP_USER+":"+u.getEmail(),u.getVipType(),second);
            });
            logger.info("初始化VIP会员信息"+VideoConstant.VIP_USER+vipUsers.size());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
