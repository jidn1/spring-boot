package com.mq.rabbitmq;

import com.mq.model.User;
import com.mq.rabbitmq.object.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/20 15:35
 * @Description: 测试对象发发送消息
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectTest {

    @Autowired
    private ObjectSender sender;

    @Test
    public void sendOject() throws Exception {
        User user=new User();
        user.setName("neo");
        user.setPass("123456");
        sender.send(user);
    }
}
