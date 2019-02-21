package com.springbootvideo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/19 16:04
 * @Description:
 */

@WebListener
public class StartupListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);
    public void contextInitialized(ServletContextEvent event) {

        logger.info("=======================【StartupListener 已加载完毕】==================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.error("===============【StartupListener已销毁】====================");
    }
}
