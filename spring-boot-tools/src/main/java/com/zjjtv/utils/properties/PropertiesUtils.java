package com.zjjtv.utils.properties;

import java.util.Properties;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/21 14:45
 * @Description:
 */
public class PropertiesUtils {

    public static Properties APP = null;
    static {
        APP = new Properties();
        try {
            APP.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
