package com.notes.study.linkedlist;

import java.beans.Customizer;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/12 16:25
 * @Description: 有序链表
 * <p>
 * 前面的链表实现插入数据都是无序的，在有些应用中需要链表中的数据有序，这称为有序链表。
 * 在有序链表中，数据是按照关键值有序排列的。一般在大多数需要使用有序数组的场合也可以使用有序链表。
 * 有序链表优于有序数组的地方是插入的速度（因为元素不需要移动），另外链表可以扩展到全部有效的使用内存，而数组只能局限于一个固定的大小中。
 */
public class OrderLinkedList {

    private class Node {
        public int data;

        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;

    public OrderLinkedList() {
        head = null;
    }

    //插入节点，并按照从小打到的顺序排列
    public void insert(int data) {
        Node node = new Node(data);

        Node perv = null;

        Node current = head;

        while (current != null && data > current.data) {
            perv = current;
            current = current.next;
        }

        if(perv == null){
            head = node;
            head.next = current;
        } else {
            perv.next = node;
            node.next = current;
        }


    }


    public void deleteHead(){
        head = head.next;
    }

    public void display(){

        Node current = head;

        while (current != null){
            System.out.println(current.data+" ");
            current = current.next;
        }

        System.out.println("");
    }


    public static void main(String[] args) {
        OrderLinkedList orderLinkedList = new OrderLinkedList();
        orderLinkedList.insert(3);
        orderLinkedList.insert(5);
        orderLinkedList.insert(1);
        orderLinkedList.insert(2);

        orderLinkedList.display();

        System.out.println("");
    }


}
