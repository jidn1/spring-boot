package com.springbootvideo.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/19 16:20
 * @Description: ip工具类
 */
public class IpUtil {

    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
