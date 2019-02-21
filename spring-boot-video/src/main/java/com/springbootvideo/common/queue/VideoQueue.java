package com.springbootvideo.common.queue;

import com.springbootvideo.model.Movie;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/20 9:45
 * @Description: 队列
 */
public class VideoQueue {

    //队列大小
    static final int QUEUE_MAX_SIZE   = 1000;

    static BlockingQueue<Movie> blockingQueue = new LinkedBlockingQueue<Movie>(QUEUE_MAX_SIZE);

    /**
     * 私有的默认构造子，保证外界无法直接实例化
     */
    private VideoQueue(){}

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder{

        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private  static VideoQueue queue = new VideoQueue();
    }

    //单例队列
    public static VideoQueue getVideoQueue(){
        return SingletonHolder.queue;
    }

    //生产入队
    public void produce(Movie m) throws InterruptedException {
        blockingQueue.put(m);
    }

    //消费出队
    public  Movie consume() throws InterruptedException {
        return blockingQueue.take();
    }
    // 获取队列大小
    public int size() {
        return blockingQueue.size();
    }
}
