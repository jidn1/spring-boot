package com.notes.study.test;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.math.LongMath;
import com.notes.common.enums.HoldModeEnum;
import com.notes.study.enums.SpotDepthTypeEnum;
import io.lettuce.core.output.ScoredValueScanOutput;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudyTest {

    private static final Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/v(\\d+)/");

    @SneakyThrows
    public static void main(String[] args) {


    }







    @Test
    public void depthMerge(){
        for(SpotDepthTypeEnum spotDepthTypeEnum : SpotDepthTypeEnum.values()){
            DealWith dealWith = DealWith.DepthEnum(spotDepthTypeEnum,new BigDecimal("39223.42"), 2);
            dealWith.step();
        }
    }


    @Test
    public void createAFlux_interval() {
        Flux.just(2, 34, 56, 75, 4, 56).collectList()
                .subscribe(list -> list.forEach(value -> System.out.print(value + "  ")));
    }

    public static String symbolSuffixPREP(String symbol) {
        symbol = substringPrefix(symbol);
        return String.format("%sPREP", symbol);
    }

    public static String substringPrefix(String symbol) {
        int end;
        // 正规表达式
        Pattern pattern = Pattern.compile("CMT_", Pattern.CASE_INSENSITIVE);
        // 去掉原始字符串开头位置的指定字符
        Matcher matcher = pattern.matcher(symbol);
        if (matcher.lookingAt()) {
            end = matcher.end();
            symbol = symbol.substring(end);
            return String.format("%sPREP", symbol);
        }
        return symbol;
    }

    public static String symbolSuffixCMT(String symbol) {
        if (symbol.endsWith("PERP")) {
            symbol = symbol.substring(0, symbol.length() - 4);
            if (symbol.endsWith("USD")) {
                return symbol;
            }
            return String.format("CMT_" + "%s", symbol);
        }
        return symbol;
    }

    Thread one = null;
    Thread two = null;
    Thread three = null;
    Thread four = null;

    @Test
    public void test() {
        try {


            two = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("thread name" + Thread.currentThread().getName());
                    }
                }
            });

            three = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("thread name" + Thread.currentThread().getName());
                    }
                }
            });

            four = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("thread name" + Thread.currentThread().getName());
                    }
                }
            });

            one = new Thread(new Runnable() {
                @Override
                public void run() {
                    throw new RuntimeException();
                }
            });

            one.start();
            two.start();
            three.start();
            four.start();
        } catch (Exception e) {

            e.printStackTrace();
            one.interrupt();
            two.interrupt();
            three.interrupt();
            four.interrupt();

        }
    }


    public static int a = 0;

    @Test
    public void test2() {

        // AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //int b = atomicInteger.incrementAndGet();
                    a++;
//                   if(b == a ){
//                       System.out.println("a="+a+", current thread name is "+ Thread.currentThread().getName());
//                   }
                }
            }).start();

        }

        System.out.println("a=====" + a);
        //  System.out.println("atomicInteger====="+atomicInteger.get());
    }

    public static boolean checkIsLoanOrReview(List<Integer> statusList) {
        List<Integer> historyList = Arrays.asList(-1, 2, 3, 4);
        if (historyList.containsAll(statusList)) {
            return false;
        }
        return true;
    }


    public static void test(Student student) {
//        if(true){
//            student.setName("tom");
//            return;
//        }
//        student.setName("jack");
//        System.out.println("123123");
    }


    public static void calc() {
        BigDecimal limit = new BigDecimal("0.125");
        BigDecimal avaliable = new BigDecimal("0.9995");
        System.out.println(limit.scale());
        if (avaliable.compareTo(limit) < 0) {
            System.out.println("不够下单");
            return;
        }

        BigDecimal[] reslt = avaliable.divideAndRemainder(limit);

        BigDecimal hold = limit.multiply(reslt[0]);
        avaliable = avaliable.subtract(hold);
        System.out.println("整数位：" + reslt[0]);
        System.out.println("余数：" + reslt[1]);
        System.out.println("hold：" + hold);
        System.out.println("avaliable：" + avaliable);
    }


    @Test
    public void test02() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String str = "2021-01-16";
        String str1 = "20210116";

        try {
            System.out.println(sdf.parse(str1));
            System.out.println(sdf.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }

        BigDecimal a = new BigDecimal("30000.000000000");
        BigDecimal b = new BigDecimal("26000.000000000");
        b.setScale(2, RoundingMode.FLOOR);
        System.out.println(b.setScale(2));
        System.out.println(b.scale());
        System.out.println(a.divide(b, b.scale(), RoundingMode.FLOOR));


//        long start = System.currentTimeMillis();
//        Date deleteDate = new Date(start - 24 * 60 * 60 * 1000*7);
//        System.out.println(sdf.format(deleteDate));
    }

    @Autowired
    ObjectMapper objectMapper;

    public <T> T toObject(String json, TypeReference<T> typeReference) {

        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
