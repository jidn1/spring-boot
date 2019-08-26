package com.allatori.timer;

import com.utils.AuthCheckUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/26 11:31
 * @Description:
 */

@Configuration
@EnableScheduling
@EnableAsync
public class CheckAuthTimer {


    @Scheduled(cron="0 0/1 * * * ?")
    public void check(){
        AuthCheckUtil.checkAuth();
    }
}
