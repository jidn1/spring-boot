package com.socket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.contants.SocketConstants;
import com.socket.exception.WebSocketException;
import com.socket.model.AcceptMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 16:28
 * @Description:
 */
public abstract class AbstractSocketHandler extends BaseHandler{


    public abstract boolean sendMessageToUser(String clientId, TextMessage message);

    public abstract boolean sendMessageToAllUsers(TextMessage message);


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        try{
            if (message.getPayloadLength() == 0) {
                return;
            }
            AcceptMessage acceptMessage = JSON.parseObject(message.getPayload().toString(), AcceptMessage.class);

            System.out.println(acceptMessage.getMsg()+":来自"+(String)session.getAttributes().get(SocketConstants.WEBSOCKET_KEY)+"的消息");

            sendMessageToUser(acceptMessage.getUserId()+"",new TextMessage("服务器收到了，hello!"));
        }catch(Exception e){
            throw new WebSocketException(e, WebSocketException.Code.SEND_ERROR);
        }
    }

    @Override
    protected void firstExecute(WebSocketSession session){
        String ID = (String)session.getAttributes().get(SocketConstants.WEBSOCKET_KEY);
        if (ID != null) {
            sendMessage(session, new TextMessage("成功建立socket连接"));
        }
    }


}
