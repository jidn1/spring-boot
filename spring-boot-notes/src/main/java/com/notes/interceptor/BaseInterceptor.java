package com.notes.interceptor;

import com.notes.SpringBootNotesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 10:44
 * @Description:
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {

    /**
     * logger instance
     */
    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("请求路径：{}", request.getRequestURI());
        return true;
    }
}
