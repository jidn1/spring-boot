package com.zjjtv.algorithm;

import java.util.Scanner;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/9/30 10:23
 * @Description: 穷举算法
 *
 * 鸡兔同笼问题
 * 在一个笼子里关着若干只鸡和若干兔子。一共有35个头，和94只脚。问在一个笼子里鸡和兔子各有多少个。
 */
public class Exhaustion {


    public static void exhaustion(int head,int foot){
        int chicken,rabbit;
        for(chicken=0;chicken<= head;chicken++){
            rabbit=head-chicken;
            if(chicken*2+rabbit*4 == foot){
                System.out.println(String.format("鸡有 %d只，兔子有%d只", chicken,rabbit));
            }
        }
    }



    @SuppressWarnings("resource")
    public static void main(String[] args) {
        int head,foot;

        System.out.println("穷举算法解决鸡兔同笼问题");
        System.out.println("输入头的个数");
        Scanner scanner = new Scanner(System.in);
        head = scanner.nextInt();
        System.out.println("输入腿的个数");
        foot = scanner.nextInt();
        exhaustion(head, foot);
    }
}
