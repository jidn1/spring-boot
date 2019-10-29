package com.web;

import com.contants.SocketConstants;
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




    /**
     * 第一个用户
     *
     * @param request
     * @return
     */
    @RequestMapping("/chat1")
    public String chat1(HttpServletRequest request) {
        // 假设用户tom登录,存储到session中
        request.getSession().setAttribute(SocketConstants.WEBSOCKET_USERID, "tom");
        return "chat1";
    }

    /**
     * 第二个用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/chat2")
    public String chat2(HttpServletRequest request) {
        // 假设用户jerry登录,存储到session中
        request.getSession().setAttribute(SocketConstants.WEBSOCKET_USERID, "jerry");
        return "chat2";
    }

    /**
     * 第三个用户登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/chat3")
    public String chat3(HttpServletRequest request) {
        // 假设用户jack登录,存储到session中
        request.getSession().setAttribute(SocketConstants.WEBSOCKET_USERID, "jack");
        return "chat3";
    }
}
