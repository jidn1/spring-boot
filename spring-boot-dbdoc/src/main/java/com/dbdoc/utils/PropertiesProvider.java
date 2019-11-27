package com.dbdoc.utils;

import com.dbdoc.service.Constants;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/27 12:31
 * @Description:
 */
public class PropertiesProvider {

    static PropertiesUtils props;

    private PropertiesProvider() {
    }

    private static void loadProperties() {
        try {
            props = new PropertiesUtils(
                    PropertiesUtils
                            .loadAllPropertiesFromClassLoader(Constants.PROPERTIES_FILE_NAME));
            for (Iterator it = props.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry entry = (Map.Entry) it.next();
                System.out.println("[Property] " + entry.getKey() + "="
                        + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return getUtils().getProperties();
    }

    private static PropertiesUtils getUtils() {
        if (props == null) {
            loadProperties();
        }
        return props;
    }

    public static String getRequiredProperty(String key) {
        return getUtils().getRequiredProperty(key);
    }

    public static String getNullIfBlank(String key) {
        return getUtils().getNullIfBlank(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return getUtils().getProperty(key, defaultValue);
    }

    public static String getProperty(String key) {
        return getUtils().getProperty(key);
    }

    public static void setProperty(String key, String value) {
        getUtils().setProperty(key, value);
    }

    public static void setProperties(Properties v) {
        props = new PropertiesUtils(v);
    }
}
