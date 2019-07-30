package com.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 17:42
 * @Description: 伪session
 */
public class SessionUtil {

    /**
     * 获得交易大厅session
     */
    public static SessionRedis getSessionByParam(Jedis jedis, HttpServletRequest request) {
//        String token = request.getParameter(TOKEN_NAME);
//        if (StringUtils.isNotEmpty(token)) {
//            String sessionStr = jedis.get(MARGIN_TOKEN_PREFIX + token);
//            if (StringUtils.isNotEmpty(sessionStr)) {
//                return JSON.parseObject(sessionStr, SessionRedis.class);
//            }
//        }
        return null;
    }
}
