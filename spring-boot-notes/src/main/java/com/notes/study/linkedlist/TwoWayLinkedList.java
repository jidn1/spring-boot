package com.notes.study.linkedlist;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/13 9:59
 * @Description: 双向链表
 */
public class TwoWayLinkedList {

    private class Node {
        private Object data;

        private Node perv;

        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    //表示链表头
    private Node head;
    //表示链表尾
    private Node tail;
    //表示链表的节点个数
    private int size;

    //构造方法
    public TwoWayLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    //在链表头增加节点
    public void addHead(Object data) {
        Node node = new Node(data);

        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.perv = node;
            head = node;
        }
        size++;
    }

    //在链表尾增加节点
    public void addTail(Object data) {
        Node node = new Node(data);

        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.perv = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    //删除链表头
    public Node deleteHead() {
        Node node = head;
        if (size > 0) {
            head = head.next;
            head.perv = null;
            size--;
        }
        return node;
    }

    //删除链表尾
    public Node deleteTail() {
        Node node = tail;
        if (size > 0) {
            tail = tail.perv;
            tail.next = null;
            size--;
        }
        return node;
    }

    //获得链表的节点个数
    public int getSize() {
        return size;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return (size == 0);
    }

    //显示节点信息
    public void display() {
        if (size > 0) {
            Node current = head;
            int templeSize = size;

            if (templeSize == 1) {
                System.out.println("[" + current.data + "]");
            }

            while (templeSize > 0) {

                System.out.println("> " + current.data);

                current = current.next;
                templeSize--;
            }

        } else {
            System.out.println("[]");
        }

    }


    public static void main(String[] args) {

    }

}
