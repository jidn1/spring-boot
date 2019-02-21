package com.springbootvideo.common.interceptor;

import com.springbootvideo.common.service.RedisService;
import com.springbootvideo.common.util.IpUtil;
import com.springbootvideo.common.util.SpringUtil;
import com.springbootvideo.common.util.VideoCache;
import com.springbootvideo.common.util.VideoDirective;
import com.springbootvideo.common.constant.VideoConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/19 16:10
 * @Description:
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    private static final String USER_AGENT = "user-agent";

    @Autowired
    private VideoDirective videoDirective;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = request.getRequestURI();

        logger.info("UserAgent: {}", request.getHeader(USER_AGENT));
        logger.info("用户访问地址: {}, 来路地址: {}", uri, IpUtil.getIpAddrByRequest(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        initSiteConfig(httpServletRequest);
        httpServletRequest.setAttribute("video",videoDirective);
    }

    private void initSiteConfig(HttpServletRequest request) {
        if(VideoCache.cache_language.isEmpty()){
            Map<String, String> language = new HashMap<String, String>();
            RedisService redisService =  SpringUtil.getBean("redisService");
            String configStr = redisService.get(VideoConstant.VIDEO_CONFIG);
            VideoCache.cache_language = language;
        }
        request.setAttribute("videoLanguage", VideoCache.cache_language);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
