package com.notes.study.cas;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jidn
 * @Date 2020/9/17
 */
public class Lock {


    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    static int a = 0;

    public static void main(String[] args) {

     Object o   = new Object();



        new Thread(()->{
            lock.writeLock().lock();
            a++;
            lock.writeLock().unlock();

            lock.readLock().lock();
            System.out.println("==="+a);
            lock.readLock().unlock();


        }).start();

        new Thread(()->{

        }).start();

        System.out.println(a);


    }
}
