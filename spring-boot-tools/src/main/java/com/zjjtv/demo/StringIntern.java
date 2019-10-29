package com.zjjtv.demo;

import com.zjjtv.print.PrintUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/5 16:16
 * @Description:
 */
public class StringIntern {

    public static void main(String[] args) {

        int a = 8;
        int b = 16;


        int c = a / (a + b);


        BigDecimal d =  new BigDecimal(String.valueOf(a)).add(new BigDecimal(String.valueOf(b)));

        BigDecimal bigDecimal = d.divide(new BigDecimal(a),2,BigDecimal.ROUND_HALF_DOWN);

        System.out.println(bigDecimal);

//        String str1 = new String("SEU")+ new String("Calvin");
//        System.out.println(str1.intern() == str1);
//        System.out.println(str1 == "SEUCalvin");
    }


    @Test
    public void test1(){
        String str1 = "abc";
        String str2 = "abc";

        System.out.println(str1 == str2);

        BigDecimal a = new BigDecimal("12.23893");
        System.out.println(a.setScale(2,BigDecimal.ROUND_DOWN));
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<String>();
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");

        list.stream().map(a->a.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
    }

    @Test
    public void testDemo3() {
        List<String> strList = Arrays.asList("YangHang", "AnXiaoHei", "LiuPengFei");

        strList.forEach(new PrintUtil()::addString);
    }

}
