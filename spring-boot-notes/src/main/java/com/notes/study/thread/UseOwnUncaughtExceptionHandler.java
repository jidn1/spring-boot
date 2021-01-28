package com.notes.study.thread;

import lombok.extern.log4j.Log4j2;

/**
 * 描述：使用刚才自己写的UncaughtExceptionHandler
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        try {
            new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-1").start();
            Thread.sleep(300);
            new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-2").start();
            Thread.sleep(300);
            new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-3").start();
            Thread.sleep(300);
            new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-4").start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void run() {
        throw new RuntimeException();
    }


    /**
     * 描述：自己的MyUncaughtExceptionHanlder
     */
    @Log4j2
    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        private String name;
        public MyUncaughtExceptionHandler(String name) {
            this.name = name;
        }
        @Override
        public void uncaughtException(Thread t, Throwable e) {

            log.info("线程异常，终止啦, {}" , t.getName());
            System.out.println(name + "捕获了异常" + t.getName() + "异常e="+e);
        }
    }

}



