package com.socket.timer;

import com.socket.handler.MyHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 16:57
 * @Description:
 */
@Configuration
@EnableScheduling
public class SocketTimer {

    @Resource
    private MyHandler myHandler;

    @Scheduled(fixedDelay = 5000)
    public void sendMessage(){
        myHandler.sendMessageToUser("888",new TextMessage("服务器发送消息给客户端"));
    }
}
