package com.socket.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 16:26
 * @Description:
 */
public class MyHandler extends AbstractSocketHandler {

    /**
     * 发送信息给指定用户
     * @param clientId
     * @param message
     * @return
     */
    @Override
    public boolean sendMessageToUser(String clientId, TextMessage message) {
        if (users.get(clientId) == null) {
            return false;
        }

        WebSocketSession session = users.get(clientId);
        System.out.println("sendMessage:" + session);
        if (!session.isOpen()) {
            return false;
        }
        sendMessage(session, message);

        return true;
    }

    /**
     * 发送消息给全体用户  广播
     * @param message
     * @return
     */
    @Override
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<String> clientIds = users.keySet();
        WebSocketSession session = null;
        for (String clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    sendMessage(session, message);
                }
            } catch (Exception e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return allSendSuccess;
    }
}
