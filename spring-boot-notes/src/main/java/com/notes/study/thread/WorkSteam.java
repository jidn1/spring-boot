package com.notes.study.thread;

import org.junit.Test;

import java.util.stream.LongStream;

public class WorkSteam {


    @Test
    public void test3(){
        long start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L, 10000000000L)
                .parallel()
                .sum();

        System.out.println(sum);

        long end = System.currentTimeMillis();

        System.out.println("耗费的时间为: " + (end - start));
    }
}
