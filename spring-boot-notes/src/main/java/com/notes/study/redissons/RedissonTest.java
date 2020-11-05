package com.notes.study.redissons;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jidn
 * @Date 2020/9/17
 */
public class RedissonTest {

    //@Autowired
    static RedissonClient redisson;

    public static void main(String[] args) throws ParseException {
//        RLock lock = redisson.getLock("MyLock");
//        lock.tryLock();

//        BigDecimal a = new BigDecimal(8);
//        BigDecimal b = new BigDecimal("12");
//        System.out.println(b.abs());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2020-09-20 12:01:10");
        java.time.LocalDate nowLocalDate = java.time.LocalDate.now();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        java.time.LocalDate time = instant.atZone(zoneId).toLocalDate();
        Integer days = 3;
        java.time.LocalDate shouldRepayDate = time.plusDays(days - 1);

//        System.out.println(shouldRepayDate);
//        System.out.println(nowLocalDate);
//
//
//
//        System.out.println(nowLocalDate.isAfter(shouldRepayDate));

        ArrayList<String> list = new ArrayList();
        list.add("tom");

        System.out.println(System.nanoTime());


//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date parse = sdf.parse("2020-09-22 12:01:10");
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(parse);
//        calendar.add(Calendar.DAY_OF_YEAR, 3);
//        Date time = calendar.getTime();
//        Date now = new Date();
//        LocalDate expiredDate = LocalDate.fromCalendarFields(calendar);
//        int dayss = Days.daysBetween(LocalDate.fromDateFields(now), expiredDate).getDays();
//        System.out.println(dayss);
//        System.out.println(sdf.format(time));


    }
}
