package com.notes.study.cas;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.concurrent.CyclicBarrier;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2020/8/13 10:06
 * @Description: compare and
 */
@Slf4j
public class MyCAS {

    private volatile int state = 0;
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static MyCAS cas = new MyCAS();

    public static void main(String[] args) {
        new Thread(new Worker(), "t-0").start();
        new Thread(new Worker(), "t-1").start();
        new Thread(new Worker(), "t-2").start();
        new Thread(new Worker(), "t-3").start();
        new Thread(new Worker(), "t-4").start();
    }


    static class Worker implements Runnable {

        @Override
        public void run() {
            log.info("请求:{}达到预定点,准备开始抢夺state",Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                if (cas.compareAndSwapState(0, 1)) {
                    log.info("当前请求:{},抢到锁!",Thread.currentThread().getName());
                } else {
                    log.info("当前请求:{},抢锁失败!",Thread.currentThread().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public final boolean compareAndSwapState(int oldValue, int newValue) {
        return unsafe.compareAndSwapInt(this, stateOffset, oldValue, newValue);

    }

    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    private static long stateOffset ;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(MyCAS.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
