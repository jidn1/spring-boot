package com.notes.study.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2020/8/13 13:21
 * @Description:
 */

public class UnsafeInstance {



    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }
}
