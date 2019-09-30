package com.zjjtv.algorithm;

import java.util.Scanner;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/9/30 10:26
 * @Description: 递推算法
 * 兔子产崽子的问题
 * 如果有两个月大的兔子以后每个月都可以产一对小兔子，而一对小兔子出生两个月后可以在生小兔子，也就是1月份出生，3月份才可以产崽子。
 * 那么假定一年内没有发生死亡事件，那么现在有一对小兔子一年后共有多少对兔子。
 */
public class Recurrence {


    public static int recurrence(int months){
        if( months == 1 || months == 2){
            return 1;
        }else{
            int m1 = recurrence(months-1);
            int m2 = recurrence(months-2);
            return m1+m2;
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        int months,rabbits;

        System.out.println("递推算法解决兔子生崽子的问题");
        System.out.println("输入月数");
        Scanner scanner = new Scanner(System.in);
        months = scanner.nextInt();
        rabbits = recurrence(months);
        System.out.println(String.format("%d月共有兔子%d对", months,rabbits));
    }
}
