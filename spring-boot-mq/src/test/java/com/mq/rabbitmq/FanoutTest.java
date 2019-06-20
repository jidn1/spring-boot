package com.mq.rabbitmq;

import com.mq.rabbitmq.fanout.FanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/20 16:17
 * @Description:
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutTest {

    @Autowired
    private FanoutSender sender;

    @Test
    public void fanoutSender() throws Exception {
        sender.send();
    }
}
