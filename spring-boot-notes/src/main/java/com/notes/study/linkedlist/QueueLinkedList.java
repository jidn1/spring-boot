package com.notes.study.linkedlist;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/12 15:49
 * @Description: 用双端链表实现队列
 */
public class QueueLinkedList {

    private DoublePointLinkedList doublePointLinkedList;

    public QueueLinkedList() {
        doublePointLinkedList = new DoublePointLinkedList();
    }


    public void insert(Object data) {
        doublePointLinkedList.addTail(data);
    }


    public void remove() {
        doublePointLinkedList.deleteHead();
    }

    public void display(){
        doublePointLinkedList.display();
    }

    public boolean isEmpty() {
        return doublePointLinkedList.isEmpty();
    }


    public int getSize() {
        return doublePointLinkedList.getSize();
    }

}
