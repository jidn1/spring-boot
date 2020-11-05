package com.notes.study.binaryTree.redBlackTree;

/**
 * @author jidn
 * @Date 2020/9/27
 */
public class RBNode<T extends Comparable<T>> {

    boolean RED = true;

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
    public String toString(){
        return ""+key+(this.color == RED ? "R":"B");
    }


    RBNode<T> root = new RBNode<T>(false,null,null,null,null);
    public static void main(String[] args) {
        

    }


    /*************对红黑树节点x进行左旋操作 ******************/
    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RBNode<T> x){
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> y = x.right;
        x.right = y.left;
        if(y.left != null){
            y.left.parent = x;
        }

        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parent = x.parent;
        if(x.parent == null){
            this.root = y;//如果x的父节点为空(即x为根节点)，则将y设为根节点
        }else{
            if(x == x.parent.left){//如果x是左子节点
                x.parent.left = y;//则也将y设为左子节点
            }else{
                x.parent.right = y;//否则将y设为右子节点
            }
        }

        //3. 将y的左子节点设为x，将x的父节点设为y
        y.left = x;
        x.parent = y;
    }



    /*************对红黑树节点y进行右旋操作 ******************/
    /*
     * 左旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBNode<T> y){
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> x = y.left;
        y.left = x.right;
        if(x.right != null){
            x.right.parent = y;
        }

        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        x.parent = y.parent;
        if(y.parent == null){
            this.root = x;//如果y的父节点为空(即y为根节点)，则旋转后将x设为根节点
        }else{
            if(y == y.parent.left){//如果y是左子节点
                y.parent.left = x;//则将x也设置为左子节点
            }else{
                y.parent.right = x;//否则将x设置为右子节点
            }
        }

        //3. 将x的左子节点设为y，将y的父节点设为y
        x.right = y;
        y.parent = x;
    }



}
