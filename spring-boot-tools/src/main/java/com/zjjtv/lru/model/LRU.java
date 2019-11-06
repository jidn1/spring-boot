package com.zjjtv.lru.model;

import java.util.HashMap;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/10/31 15:01
 * @Description: 我们定义一个LRU类，然后定义它的大小、容量、头节点、尾节点等部分，然后一个基本的构造方法
 */
public class LRU<K, V> {

    private int currentSize;//当前的大小
    private int capcity;//总容量
    private HashMap<K, Node> caches;//所有的node节点
    private Node first;//头节点
    private Node last;//尾节点

    public LRU(int size) {
        currentSize = 0;
        this.capcity = size;
        caches = new HashMap<K, Node>(size);
    }


    /**
     * 添加元素的时候首先判断是不是新的元素，如果是新元素，判断当前的大小是不是大于总容量了，
     * 防止超过总链表大小，如果大于的话直接抛弃最后一个节点，
     * 然后再以传入的key\value值创建新的节点。
     * 对于已经存在的元素，直接覆盖旧值，再将该元素移动到头部，然后保存在map中
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        Node node = caches.get(key);
        //如果新元素
        if (node == null) {
            //如果超过元素容纳量
            if (caches.size() >= capcity) {
                //移除最后一个节点
                caches.remove(last.key);
                removeLast();
            }
            //创建新节点
            node = new Node(key,value);
        }
        //已经存在的元素覆盖旧值
        node.value = value;
        //把元素移动到首部
        moveToHead(node);
        caches.put(key, node);
    }


    /**
     * 通过key值来访问元素，主要的做法就是先判断如果是不存在的，直接返回null。
     * 如果存在，把数据移动到首部头节点，然后再返回旧值
     * @param key
     * @return
     */
    public Object get(K key) {
        Node node = caches.get(key);
        if (node == null) {
            return null;
        }
        //把访问的节点移动到首部
        moveToHead(node);
        return node.value;
    }


    /**
     * 把当前节点移动到首部
     * @param node
     */
    private void moveToHead(Node node) {
        if (first == node) {
            return;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node == last) {
            last = last.pre;
        }
        if (first == null || last == null) {
            first = last = node;
            return;
        }
        node.next = first;
        first.pre = node;
        first = node;
        first.pre = null;
    }


    /**
     * 根据key移除节点
     * @param key
     * @return
     */
    public Object remove(K key) {
        Node node = caches.get(key);
        if (node != null) {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node == first) {
                first = node.next;
            }
            if (node == last) {
                last = node.pre;
            }
        }
        return caches.remove(key);
    }


    /**
     * 清除所有节点
     */
    public void clear() {
        first = null;
        last = null;
        caches.clear();
    }


    /**
     * 移除最后一个节点
     */
    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = first;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LRU<Integer, String> lru = new LRU<Integer, String>(5);
        lru.put(1, "a");
        lru.put(2, "b");
        lru.put(3, "c");
        lru.put(4,"d");
        lru.put(5,"e");
        System.out.println("原始链表为:"+lru.toString());

        lru.get(4);
        System.out.println("获取key为4的元素之后的链表:"+lru.toString());

        lru.put(6,"f");
        System.out.println("新添加一个key为6之后的链表:"+lru.toString());

        lru.remove(3);
        System.out.println("移除key=3的之后的链表:"+lru.toString());

        Integer.parseInt("1",1);
    }
}
