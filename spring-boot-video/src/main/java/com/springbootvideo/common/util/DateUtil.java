package com.springbootvideo.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/27 13:55
 * @Description: 日期工具类
 */
public class DateUtil {


    /**
     * 增加n天后的日期
     * @param date
     * @param n
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);// 增加n天
        return calendar.getTime();
    }

    /**
     * 增加n个月后的日期
     * @param date
     * @param n
     * @return
     */
    public static Date addMonth(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);// 增加n个月
        return calendar.getTime();
    }

    /**
     * 比较两个时间相差多少秒
     * @param date1 较大时间
     * @param date2 较小时间
     * @return
     */
    public static int compareDateSecond(Date date1,Date date2){
        Calendar dateOne=Calendar.getInstance();
        dateOne.setTime(date1);	//设置date1
        Calendar dateTwo=Calendar.getInstance();
        dateTwo.setTime(date2);	//设置date2
        long timeOne=dateOne.getTimeInMillis();
        long timeTwo=dateTwo.getTimeInMillis();
        long minute=(timeOne-timeTwo)/(1000);//转化second
        return Long.valueOf(minute).intValue();
    }

    /**
     * 比较两个时间相差多少分钟
     * @param date1 较大时间
     * @param date2 较小时间
     * @return
     */
    public static int compareDateMinute(Date date1,Date date2){
        Calendar dateOne=Calendar.getInstance();
        dateOne.setTime(date1);	//设置date1
        Calendar dateTwo=Calendar.getInstance();
        dateTwo.setTime(date2);	//设置date2
        long timeOne=dateOne.getTimeInMillis();
        long timeTwo=dateTwo.getTimeInMillis();
        long minute=(timeOne-timeTwo)/(1000*60);//转化minute
        return Long.valueOf(minute).intValue();
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return
     * @throws ParseException
     */
    public static int compareDateDay(Date smdate,Date bdate) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse("2019-02-28 00:00:00");
        System.out.println(compareDateMinute(parse,new Date()));
    }

}
