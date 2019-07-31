package com.web;

import com.util.GeneratorUtil;
import com.util.RedisUtils;
import com.util.SessionRedis;
import com.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 20:10
 * @Description:
 */
@Controller
public class IndexController {

    @Resource
    private JedisPool jedisPool;

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        try (Jedis jedis = jedisPool.getResource()){


            SessionRedis sessionRedis = new SessionRedis();
            String marginToken = "0-" + GeneratorUtil.MarginToken.getCount();
            sessionRedis.setMarginToken(marginToken);
            sessionRedis.setUserId("888");
            SessionUtil.createSession(jedis, sessionRedis, response);
            request.setAttribute("token",marginToken);
            System.out.println("token============"+marginToken);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }
}
