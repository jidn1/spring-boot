package com.notes.study.linkedlist;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/12 13:59
 * @Description: 双端链表的具体实现
 * 对于单项链表，我们如果想在尾部添加一个节点，那么必须从头部一直遍历到尾部，找到尾节点，
 * 然后在尾节点后面插入一个节点。这样操作很麻烦，如果我们在设计链表的时候多个对尾节点的引用，那么会简单很多。
 */
public class DoublePointLinkedList {

    private class Node {

        private Node prev;
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }

    }

    private Node head;//头节点

    private Node tail;//尾节点

    private int size;//节点个数

    public DoublePointLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    //添加头节点
    public void addHead(Object data) {
        Node node = new Node(data);

        if (size == 0) {
            head = node;
            tail = node;
            size++;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
            size++;
        }
    }

    //添加尾节点
    public void addTail(Object data) {
        Node node = new Node(data);
        if (size == 0) {
            head = node;
            tail = node;
            size++;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
            size++;
        }

    }


    public boolean deleteHead() {

        if (size == 0) {
            return false;
        }

        if (head.next == null) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }

        size--;
        return true;
    }

    public boolean deleteTail() {
        if (size == 0) {
            return false;
        }
        if (tail.prev == null) {
            tail = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return true;
    }

    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return (size == 0);
    }


    public void display() {
        if (size > 0) {
            Node node = head;
            int templeSize = size;
            if (templeSize == 1) {
                System.out.println("【" + node.data + "】");
                return;
            }

            while (templeSize > 0) {

                if (node.equals(head)) {
                    System.out.println(node.data + "-->");
                } else if (node.next == null) {
                    System.out.println("-->" + node.data );
                } else {
                    System.out.println(node.data + "-->");
                }

                node = node.next;
                templeSize--;
            }

        } else {
            System.out.println("[]");
        }

    }


    public static void main(String[] args) {
//        DoublePointLinkedList dpll = new DoublePointLinkedList();
//        dpll.addHead("a");
//        dpll.addHead("b");
//        dpll.addHead("c");
//
//        dpll.display();
//        System.out.println("");
//        dpll.addTail("d");
//
//        dpll.display();
//        System.out.println("");
//
//        dpll.deleteHead();
//
//        dpll.display();



        DoublePointLinkedList dpll = new DoublePointLinkedList();
        dpll.addTail("a");
        dpll.addTail("b");
        dpll.addTail("c");

        dpll.display();
        System.out.println("");
        dpll.addHead("d");

        dpll.display();
        System.out.println("");

        dpll.deleteTail();

        dpll.display();

        System.out.println("");
        dpll.addTail("c");
        dpll.display();

    }


}
