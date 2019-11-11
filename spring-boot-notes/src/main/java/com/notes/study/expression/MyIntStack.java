package com.notes.study.expression;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/11 11:42
 * @Description: 自定义一个栈 用来存储 运算符栈 和 运算结果栈
 */
public class MyIntStack {

    private int[] array;

    private int maxSize;

    private int top;

    public MyIntStack(int size){
        array = new int[size];
        maxSize = size;
        top = -1;
    }

    //压入数据
    public void push(int value){
        if(top < maxSize - 1){
            array[++top] = value;
        }
    }

    //弹出栈顶数据
    public int pop(){
        return array[top --];
    }

    //访问栈顶数据
    public int peek(){
        return array[top];
    }


    //查看指定位置的元素
    public int peekN(int n){
        return array[n];
    }


    //为了便于后面分解展示栈中的内容，我们增加了一个遍历栈的方法(实际上栈只能访问栈顶元素的)
    public void displayStack(){
        System.out.print("Stack(bottom-->top):");
        for(int i = 0 ; i < top+1; i++){
            System.out.print(peekN(i));
            System.out.print(' ');
        }
        System.out.println("");
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return (top == -1);
    }

    //判断栈是否满了
    public boolean isFull(){
        return (top == maxSize-1);
    }

}
