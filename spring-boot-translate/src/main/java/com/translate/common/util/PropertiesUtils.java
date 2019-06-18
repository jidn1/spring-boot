package com.translate.common.util;

import java.util.Properties;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/18 16:05
 * @Description:
 */
public class PropertiesUtils {
    public static Properties APP = null;

    static {
        APP = new Properties();

        try {
            APP.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
}
