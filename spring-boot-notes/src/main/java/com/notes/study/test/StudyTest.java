package com.notes.study.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StudyTest {


    public static void main(String[] args) {

//        BigDecimal a = new BigDecimal(12.3);
//
//        System.out.println(a.movePointRight(1).setScale(6,BigDecimal.ROUND_DOWN));
//
//        System.out.println(a.setScale(6,BigDecimal.ROUND_DOWN).abs());
//
//        BigDecimal four = new BigDecimal("4");
//        System.out.println(four.divideAndRemainder(new BigDecimal("2"))[0]);

//        List<Integer> historyList = Arrays.asList(1,0);
//        System.out.println(checkIsLoanOrReview(historyList));
      calc();
//        int n = 1;
//        Student student = new Student();
//        student.setName("hankesi");
//        test(student);
//        System.out.println(student.getName());
    }

    Thread one = null;
    Thread two = null;
    Thread three = null;
    Thread four = null;
    @Test
    public void test(){
     try {




         two = new Thread(new Runnable() {
             @Override
             public void run() {
                 while (true) {
                     System.out.println("thread name"+Thread.currentThread().getName());
                 }
             }
         });

         three = new Thread(new Runnable() {
             @Override
             public void run() {
                 while (true){
                     System.out.println("thread name"+Thread.currentThread().getName());
                 }
             }
         });

         four = new Thread(new Runnable() {
             @Override
             public void run() {
                 while (true){
                     System.out.println("thread name"+Thread.currentThread().getName());
                 }
             }
         });

         one = new Thread(new Runnable() {
             @Override
             public void run() {
                 throw new  RuntimeException();
             }
         });

         one.start();
         two.start();
         three.start();
         four.start();
     } catch (Exception e){

         e.printStackTrace();
         one.interrupt();
         two.interrupt();
         three.interrupt();
         four.interrupt();

     }
    }



    public static int a = 0;
    @Test
    public void test2(){

       // AtomicInteger atomicInteger = new AtomicInteger(0);
        for(int i = 0;i < 1000;i++){
           new Thread(new Runnable() {
               @Override
               public void run() {
                   //int b = atomicInteger.incrementAndGet();
                   a ++;
//                   if(b == a ){
//                       System.out.println("a="+a+", current thread name is "+ Thread.currentThread().getName());
//                   }
               }
           }).start();

        }

        System.out.println("a====="+a);
      //  System.out.println("atomicInteger====="+atomicInteger.get());
    }

    public static boolean checkIsLoanOrReview(List<Integer> statusList){
        List<Integer> historyList = Arrays.asList(-1,2,3,4);
        if (historyList.containsAll(statusList)) {
            return false;
        }
        return true;
    }


    public static  void test(Student student){
        if(true){
            student.setName("tom");
            return;
        }
        student.setName("jack");
        System.out.println("123123");
    }



    public static  void calc(){
        BigDecimal limit = new BigDecimal("0.125");
        BigDecimal avaliable = new BigDecimal("0.9995");
        System.out.println(limit.scale());
        if(avaliable.compareTo(limit)<0) {
            System.out.println("不够下单") ;
            return;
        }

        BigDecimal[] reslt = avaliable.divideAndRemainder(limit);

        BigDecimal  hold = limit.multiply(reslt[0]);
        avaliable  = avaliable.subtract(hold);
        System.out.println("整数位：" +reslt[0]);
        System.out.println("余数：" +reslt[1]);
        System.out.println("hold：" +hold);
        System.out.println("avaliable：" +avaliable);
    }



    @Test
    public void test02(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String str = "2021-01-16";
        String str1 = "20210116";

        try {
            System.out.println(sdf.parse(str1));
            System.out.println(sdf.parse(str));
        } catch (Exception e){
            e.printStackTrace();
        }

        BigDecimal a = new BigDecimal("30000.000000000");
        BigDecimal b = new BigDecimal("26000.000000000");
        b.setScale(2,RoundingMode.FLOOR);
        System.out.println(b.setScale(2));
        System.out.println(b.scale());
        System.out.println(a.divide(b,b.scale(), RoundingMode.FLOOR));


//        long start = System.currentTimeMillis();
//        Date deleteDate = new Date(start - 24 * 60 * 60 * 1000*7);
//        System.out.println(sdf.format(deleteDate));
    }

    @Autowired
    ObjectMapper objectMapper;

    public  <T> T toObject(String json, TypeReference<T> typeReference){

        try {
            return objectMapper.readValue(json,typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
