package com.springbootvideo.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/18 18:23
 * @Description: 配置首页
 */
@Configuration
public class MyAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private BaseInterceptor baseInterceptor;

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor);
    }

}
