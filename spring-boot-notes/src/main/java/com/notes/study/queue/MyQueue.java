package com.notes.study.queue;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 16:48
 * @Description:
 */
public class MyQueue {

    private Object[] queueArray;
    //队列总大小
    private int maxSize;
    //前端
    private int front;
    //后端
    private int rear;
    //队列中元素的实际数目
    private int nItems;

    public MyQueue(int s){
        queueArray = new Object[s];
        maxSize = s;
        front = 0;
        rear = -1;
        nItems = 0;
    }


    public void insert(int value){
        if(isFull()){
            System.out.println("队列已满");
        }
        //如果队列尾部指向顶了，那么循环回来，执行队列的第一个元素
        if(rear == maxSize -1){
            rear = -1;
        }
        queueArray[++rear] = value;
        nItems++;
    }


    public Object remove(){
        Object remoteObject = null;
        if(isEmpty()){
            System.out.println("队列已空");
            return remoteObject;
        }
        remoteObject = queueArray[front];
        queueArray[front] = null;
        front++;

        if(front == maxSize){
            front = 0;
        }
        nItems--;

        return remoteObject;
    }


    //查看对头数据
    public Object peekFront(){
        return queueArray[front];
    }

    public boolean isEmpty(){
        return (nItems == 0);
    }

    public boolean isFull(){
        return  (nItems == maxSize);
    }

    //返回队列的大小
    public int getSize(){
        return nItems;
    }


    public static void main(String[] args) {
        MyQueue queue = new MyQueue(3);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);//queArray数组数据为[1,2,3]

        System.out.println(queue.peekFront()); //1
        queue.remove();//queArray数组数据为[null,2,3]
        System.out.println(queue.peekFront()); //2

        queue.insert(4);//queArray数组数据为[2,3,4]
        System.out.println(queue.peekFront());
        queue.insert(5);//队列已满,queArray数组数据为[4,2,3]
    }
}
