package com.mq.rabbitmq.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/20 15:27
 * @Description:
 */
@Component
@RabbitListener(queues = "neo")
public class NeoReceiver1 {


    @RabbitHandler
    public void process(String neo) {
        System.out.println("Receiver 1: " + neo);
    }
}
