package com.zjjtv.study;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/9/11 15:43
 * @Description: 字符串左旋转
 */
public class leftRotateString {


    public static void main(String[] args) {
        String a = "abcdefg";

        int offset = 1;
        char[] str = a.toCharArray();

        testDemo_one(str,offset);
        System.out.println(a);
    }


    public static void testDemo_one(char[] str, int offset) {

        if (str.length == 0){
            return;
        }

        if (offset % str.length == 0){
            return;
        }



        offset = offset % str.length;
        char[] temp = new char[offset];

        for (int i = str.length - offset; i < str.length; i++) {
            temp[i - str.length + offset] = str[i];
        }
        for (int i = str.length - offset - 1; i >= 0; i--) {
            str[i + offset] = str[i];
        }
        for (int i = 0; i < offset; i++) {
            str[i] = temp[i];
        }


    }
}
