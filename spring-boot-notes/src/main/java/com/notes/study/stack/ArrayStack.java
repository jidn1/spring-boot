package com.notes.study.stack;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 11:14
 * @Description:
 *  栈（英语：stack）又称为堆栈或堆叠，栈作为一种数据结构，是一种只能在一端进行插入和删除操作的特殊线性表。
 * 它按照先进后出的原则存储数据，先进入的数据被压入栈底，最后的数据在栈顶，
 * 需要读数据的时候从栈顶开始弹出数据（最后一个数据被第一个读出来）。
 * 栈具有记忆作用，对栈的插入与删除操作中，不需要改变栈底指针。
 */
public class ArrayStack {

    private Object[] elementData;

    private int top;

    private int size;


    public ArrayStack(){
        this.elementData = new Object[10];
        this.size = 10;
        this.top = -1;
    }

    public ArrayStack(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException("栈初始容量不能小于0: "+initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
        this.size = initialCapacity;
        this.top = -1;
    }


    public Object push(Object value){
        isGrow(top + 1);
        elementData[++top] = value;
        return value;
    }

    public Object pop(){
        Object obj = peek();
        remove(top);
        return obj;
    }


    public Object peek(){
        if(top == -1){
            throw new EmptyStackException();
        }
        return elementData[top];
    }


    public void remove(int i){
        if(i == -1){
            System.out.println();
        }
        elementData[i] = null;
        this.top--;

    }

    public boolean isEmpty(){
        return (top == -1);
    }



    public boolean isGrow(int topS){
        int oldSize = size;
        if(topS >= oldSize){
            int newSize = 0;

            if((oldSize << 1) > Integer.MAX_VALUE){
                newSize = Integer.MAX_VALUE;
            } else {
                newSize = oldSize << 1;
            }

            elementData = Arrays.copyOf(elementData,newSize);
            this.size = newSize;
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
//        ArrayStack stack = new ArrayStack(3);
//        stack.push(1);
//        //System.out.println(stack.peek());
//        stack.push(2);
//        stack.push(3);
//        stack.push("abc");
//        System.out.println(stack.peek());
//        stack.pop();
//        stack.pop();
//        stack.pop();
//        System.out.println(stack.peek());


//        ArrayStack stack = new ArrayStack();
//        String str = "how are you";
//        char[] cha = str.toCharArray();
//        for(char c : cha){
//            stack.push(c);
//        }
//        while(!stack.isEmpty()){
//            System.out.print(stack.pop());
//        }




        ArrayStack stack = new ArrayStack(3);
        String str = "12<a[b{c}]>";
        char[] cha = str.toCharArray();
        for(char c : cha){
            switch (c) {
                case '{':
                case '[':
                case '<':
                    stack.push(c);
                    break;
                case '}':
                case ']':
                case '>':
                    if(!stack.isEmpty()){
                        char ch = stack.pop().toString().toCharArray()[0];
                        System.out.println(ch);
                        if(c=='}' && ch != '{'
                                || c==']' && ch != '['
                                || c==')' && ch != '('){
                            System.out.println("Error:"+ch+"-"+c);
                        }
                    }
                    break;
                default:
                    System.out.println(c);
                    break;
            }
        }
    }
}
