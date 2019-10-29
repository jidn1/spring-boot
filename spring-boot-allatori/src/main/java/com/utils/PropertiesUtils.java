package com.utils;

import java.util.Properties;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/26 11:44
 * @Description:
 */
public class PropertiesUtils {

    public static Properties APP = null;
    static {
        APP = new Properties();
        try {
            APP.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("BOOT-INF/app.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
