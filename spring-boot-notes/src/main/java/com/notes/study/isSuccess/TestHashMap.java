package com.notes.study.isSuccess;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2020/3/5 14:48
 * @Description:
 */
public class TestHashMap {
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        map.put("hollis", "hollischuang");


        Class<?> mapType = map.getClass();

        Method capacity = mapType.getDeclaredMethod("capacity");

        capacity.setAccessible(true);

        System.out.println("capacity : " + capacity.invoke(map));



        Field size = mapType.getDeclaredField("size");

        size.setAccessible(true);

        System.out.println("size : " + size.get(map));


        String name = "";


        String age = "";
    }
}
