package com.zjjtv.binaryTree.redbacktree;

import lombok.Data;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/5/17 17:31
 * @Description:
 */
@Data
public class RBNode<T extends Comparable<T>> {

    boolean color;//颜色
    T key;//关键值
    RBNode<T> left;//左子节点
    RBNode<T> right;//右子节点
    RBNode<T> parent;//父节点

    public RBNode(boolean color,T key,RBNode<T> parent,RBNode<T> left,RBNode<T> right){
        this.color = color;
        this.key = key;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    //获得节点的关键值
    public T getKey(){
        return key;
    }
    //打印节点的关键值和颜色信息
    @Override
    public String toString(){
        return ""+key+(this.color == true ? "R":"B");
    }
}
