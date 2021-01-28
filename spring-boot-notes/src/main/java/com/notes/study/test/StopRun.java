package com.notes.study.test;

public class StopRun implements Runnable {

    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + ".....run");
        }
    }

}
