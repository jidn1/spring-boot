package com.util;

import com.alibaba.fastjson.JSON;
import com.contants.SocketConstants;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 17:42
 * @Description: 伪session
 */
public class SessionUtil {


    /**
     * 创建session
     */
    public static void createSession(Jedis jedis, SessionRedis session, HttpServletResponse response) {
        jedis.set(SocketConstants.MARGIN_TOKEN_PREFIX + session.getMarginToken(), JSON.toJSONString(session));
        jedis.expire(SocketConstants.MARGIN_TOKEN_PREFIX + session.getMarginToken(), 1800);
        Cookie cookie = new Cookie(SocketConstants.TOKEN_NAME, session.getMarginToken());
        response.addCookie(cookie);
    }


    /**
     * 获得session
     */
    public static SessionRedis getSessionByParam(Jedis jedis, HttpServletRequest request) {
        String token = request.getParameter(SocketConstants.TOKEN_NAME);
        if (StringUtils.isNotEmpty(token)) {
            String sessionStr = jedis.get(SocketConstants.MARGIN_TOKEN_PREFIX + token);
            if (StringUtils.isNotEmpty(sessionStr)) {
                return JSON.parseObject(sessionStr, SessionRedis.class);
            }
        }
        return null;
    }
}
