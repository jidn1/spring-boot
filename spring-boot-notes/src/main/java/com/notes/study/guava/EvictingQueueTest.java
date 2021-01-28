package com.notes.study.guava;

import com.google.common.collect.EvictingQueue;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class EvictingQueueTest {

    @Test
    public void test(){
        EvictingQueue<Integer> queue = EvictingQueue.create(3);
        for (int i = 0; i < 6; i++) {
            queue.add(i);
            System.out.println("添加元素：" + i);
            System.out.println("当前队列中元素集合：" + StringUtils.join(queue.iterator(), ',') + "\n");
        }


    }
}
