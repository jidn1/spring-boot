package com.springbootvideo.common.util;

import org.springframework.stereotype.Component;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/19 17:30
 * @Description: 公共函数
 */
@Component
public class VideoDirective {

    public static String player(String sub) {
        return site_url("player/" + sub.toString());
    }

    public static String menu_url(String url){
        return site_url(url);
    }

    public static String site_url(String sub) {
        return "/springboot_video/" + sub+".html";
    }
}
