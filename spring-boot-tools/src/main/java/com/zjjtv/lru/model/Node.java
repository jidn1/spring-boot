package com.zjjtv.lru.model;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/10/31 14:56
 * @Description: 定义基本的链表操作节点
 */
public class Node {

    //键
    Object key;
    //值
    Object value;
    //上一个节点
    Node pre;
    //下一个节点
    Node next;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}
