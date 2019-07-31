package com.socket.handler;

import com.alibaba.fastjson.JSON;
import com.contants.SocketConstants;
import com.socket.exception.WebSocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 16:27
 * @Description:
 */
public abstract class BaseHandler implements WebSocketHandler {

    public static final Logger log = LoggerFactory.getLogger(BaseHandler.class);

    /**
     * 用户信息汇总
     */
    protected Map<String, List<WebSocketSession>> current = new ConcurrentHashMap<>();

    protected static final Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        addSession(session);
    }



    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("连接出错");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        try {
            removeSession(session);
            String userId = (String) session.getAttributes().get(SocketConstants.WEBSOCKET_KEY);

            log.info("用户【{}】已断开连接", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    protected abstract void firstExecute(WebSocketSession session);

    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get(SocketConstants.WEBSOCKET_USERID);
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }

    protected void addSession(WebSocketSession session) {
        System.out.println("成功建立连接");
        synchronized (session.getId()) {
            String ID = (String) session.getAttributes().get(SocketConstants.WEBSOCKET_KEY);
            System.out.println(ID);
            if (ID != null) {
                users.put(ID, session);
            }
            System.out.println("当前在线人数："+users.size());
        }
        firstExecute(session);
    }

    protected void removeSession(WebSocketSession session) {
        String msg = (String) session.getAttributes().get(SocketConstants.WEBSOCKET_KEY);
        synchronized (session.getId()) {
            List<WebSocketSession> sessions = current.get(msg);
            sessions.remove(session);
            if (sessions.size() == 0) {
                current.remove(msg);
            }
        }
    }

    protected boolean sendMessage(WebSocketSession session, Object obj) {
        try {
            synchronized (session.getId()) {
                if (session.isOpen()) {
                    session.sendMessage(hexStr2Byte(JSON.toJSONString(obj)));
                    return true;
                }
            }
        } catch (IOException e) {
            throw new WebSocketException(e, WebSocketException.Code.SEND_MSG);
        }
        return false;
    }

    /**
     * 转二进制码
     */
    protected BinaryMessage hexStr2Byte(String json) {
        BinaryMessage binaryMessage = new BinaryMessage(json.getBytes());
        if (binaryMessage.getPayloadLength() > 40000) {
            System.out.println();
        }
        return binaryMessage;
    }
}
