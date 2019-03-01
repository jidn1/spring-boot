package com.springbootvideo.listener;

import com.springbootvideo.common.quartz.QuartzJob;
import com.springbootvideo.common.quartz.QuartzManager;
import com.springbootvideo.common.quartz.ScheduleJob;
import com.springbootvideo.common.util.SpringUtil;
import com.springbootvideo.service.IConfigService;
import com.springbootvideo.service.IMovieService;
import com.springbootvideo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

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

    private ApplicationContext app;

    public void contextInitialized(ServletContextEvent event) {
        app = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

        //初始化缓存fh_config
        IConfigService configService = (IConfigService)app.getBean("configService");
        configService.initCache();

        //电影数据初始化
        IMovieService movieService = (IMovieService)app.getBean("movieService");
        movieService.initTopHome();
        movieService.initMovieAll(null);

        //初始化VIP信息
        IUserService userService = (IUserService)app.getBean("userService");
        userService.initVip();


        ScheduleJob initOssJob = new ScheduleJob();
        initOssJob.setSpringId("movieService");
        initOssJob.setMethodName("initOssUrl");
        QuartzManager.addJob("initOssJob", initOssJob, QuartzJob.class, "0 59 23 * * ?");

        logger.info("=======================【StartupListener 已加载完毕】==================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.error("===============【StartupListener已销毁】====================");
    }
}
