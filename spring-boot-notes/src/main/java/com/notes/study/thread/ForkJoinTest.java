package com.notes.study.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算 0,100000000的总和
 */
public class ForkJoinTest extends RecursiveTask<Integer> {


    private static final long THURSHOLD = 1000;      //临界值
    private int start;
    private int end;


    public ForkJoinTest(int start,int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int length = end - start;
        //如果任务分割的足够小就计算任务
        if(length <= THURSHOLD) {
            int sum = 0;
            for(int i = start;i <= end;i++) {
                sum+=i ;
            }
            return sum;
        } else {
            //任务大于阈值，分割成两个子任务
            int middle = (start + end) / 2;

            ForkJoinTest left = new ForkJoinTest(start, middle );
            left.fork();   //执行子任务


            ForkJoinTest right = new ForkJoinTest(middle + 1, end );
            right.fork();    //执行子任务
            return left.join() + right.join();   //等待子任务执行完并结果合并
        }
    }
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        //计算  0,100000000的总和
        ForkJoinTask<Integer> task = new ForkJoinTest(0,100000000);
        long sum = pool.invoke(task);
        System.out.println("sum:"+sum);
    }

}
