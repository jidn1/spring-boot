package com.socket;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.contants.SocketConstants;
import com.socket.exception.WebSocketException;
import com.util.RedisUtils;
import com.util.SessionRedis;
import com.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import redis.clients.jedis.Jedis;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 16:16
 * @Description: 进入Handler之前的拦截
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    public static final Logger log = LoggerFactory.getLogger(WebSocketInterceptor.class);


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> sessionMap) throws Exception {
        log.debug("websocket handler 前的操作开始。。。。");
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
        try (Jedis jedis = RedisUtils.JEDIS_POOL.getResource()){
            SessionRedis sessionRedis = SessionUtil.getSessionByParam(jedis, httpServletRequest);
            if(null != sessionRedis){
                System.out.println("当前session的ID="+sessionRedis.getUserId());
                sessionMap.put(SocketConstants.WEBSOCKET_USERID,sessionRedis.getUserId());
            }
        } catch (Exception e){
            throw new WebSocketException(e, WebSocketException.Code.REQ_PUSH);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("进来webSocket的afterHandshake拦截器！");
    }
}
