package com.translate.google.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/12 16:21
 * @Description: 正则工具类
 */
public class RegularUtil {


    public static String extractByStartAndEnd(String str, String startStr, String endStr) {
        String regEx = startStr + ".*?"+endStr;
        String group = findMatchString(str, regEx);
        String trim = group.replace(startStr, "").replace(endStr, "").trim();
        return trim(trim);
    }

    public static String findMatchString(String str, String regEx) {
        try {
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regEx);
            // 忽略大小写的写法
            Matcher matcher = pattern.matcher(str);
            // 字符串是否与正则表达式相匹配
            return findFristGroup(matcher);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String findFristGroup(Matcher matcher) {
        matcher.find();
        return matcher.group(0);
    }

    public static String removeAllBlank(String s){
        String result = "";
        if(null!=s && !"".equals(s)){
            result = s.replaceAll("[　*| *| *|//s*]*", "");
        }
        return result;
    }

    public static String trim(String s){
        String result = "";
        if(null!=s && !"".equals(s)){
            result = s.replaceAll("^[　*| *| *|//s*]*", "").replaceAll("[　*| *| *|//s*]*$", "");
        }
        return result;
    }
}
