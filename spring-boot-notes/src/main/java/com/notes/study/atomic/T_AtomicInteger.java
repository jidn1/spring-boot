package com.notes.study.atomic;

import com.notes.study.cas.MyCAS;
import com.notes.study.cas.UnsafeInstance;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jidn
 * @Date 2020/9/16
 */
public class T_AtomicInteger {


    public volatile static int total = 0;

    public volatile int age = 0;

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        T_AtomicInteger t = new T_AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
//                    atomicInteger.getAndIncrement();
                    atomicInteger.incrementAndGet();
                }
//                countDownLatch.countDown();
            }).start();
        }

//        countDownLatch.await();
//        System.out.println(total);
        System.out.println(atomicInteger.get());
    }

    public final boolean compareAndSwapState(int oldValue, int newValue) {
        return unsafe.compareAndSwapInt(this, stateOffset, oldValue, newValue);

    }

    static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    private static long stateOffset ;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(T_AtomicInteger.class.getDeclaredField("age"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
