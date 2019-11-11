package com.notes.study.linkedlist;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/11 15:57
 * @Description: 用单向链表实现栈
 * <p>
 * 栈的pop()方法和push()方法，对应于链表的在头部删除元素deleteHead()以及在头部增加元素addHead()。
 */
public class StackSingleLink {

    private SingleLinkedList link;

    public StackSingleLink() {
        link = new SingleLinkedList();
    }

    //添加元素
    public void push(Object obj) {
        link.addHead(obj);
    }

    //移除栈顶元素
    public Object pop() {
        Object obj = link.deleteHead();
        return obj;
    }

    //判断是否为空
    public boolean isEmpty() {
        return link.isEmpty();
    }

    //打印栈内元素信息
    public void display() {
        link.display();
    }

}
