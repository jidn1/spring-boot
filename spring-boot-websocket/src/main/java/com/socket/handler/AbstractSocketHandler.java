package com.socket.handler;

import com.alibaba.fastjson.JSONObject;
import com.contants.SocketConstants;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 16:28
 * @Description:
 */
public abstract class AbstractSocketHandler extends BaseHandler{


    public abstract boolean sendMessageToUser(String clientId, TextMessage message);

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        try{
            JSONObject jsonobject = JSONObject.parseObject(webSocketMessage.getPayload().toString());
            System.out.println(jsonobject.get("id"));
            System.out.println(jsonobject.get("message")+":来自"+(String)webSocketSession.getAttributes().get(SocketConstants.WEBSOCKET_KEY)+"的消息");
            sendMessageToUser(jsonobject.get("id")+"",new TextMessage("服务器收到了，hello!"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void firstExecute(WebSocketSession session){
        System.out.println("成功建立连接");
        String ID = session.getUri().toString().split("ID=")[1];
        System.out.println(ID);
        if (ID != null) {
            users.put(ID, session);
            sendMessage(session, new TextMessage("成功建立socket连接"));
            System.out.println(ID);
            System.out.println(session);
        }

        System.out.println("当前在线人数："+users.size());
    }


}
