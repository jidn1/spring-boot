package com.notes.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 10:13
 * @Description:
 */
@Configuration
public class MyConfigurer implements WebMvcConfigurer {

    @Autowired
    private BaseInterceptor baseInterceptor;

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/notes/index" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    }

    /**
     * 重写添加拦截器方法并添加配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor).addPathPatterns("/**");
    }

}
