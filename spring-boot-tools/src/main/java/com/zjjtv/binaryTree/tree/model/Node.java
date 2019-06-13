package com.zjjtv.binaryTree.tree.model;

import lombok.Data;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/5/17 14:14
 * @Description:
 */
@Data
public class Node {

    public int data;   //节点数据
    public Node leftChild; //左子节点的引用
    public Node rightChild; //右子节点的引用
    public boolean isDelete;//表示节点是否被删除

    public Node(int data){
        this.data = data;
    }
    //打印节点内容
    public void display(){
        System.out.println(data);
    }
}
