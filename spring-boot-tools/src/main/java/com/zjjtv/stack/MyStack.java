package com.zjjtv.stack;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/6 19:03
 * @Description: 这个栈是用数组实现的，内部定义了一个数组，一个表示最大容量的值以及一个指向栈顶元素的top变量。
 *           构造方法根据参数规定的容量创建一个新栈，push()方法是向栈中压入元素，指向栈顶的变量top加一，
 *           使它指向原顶端数据项上面的一个位置，并在这个位置上存储一个数据。
 *           pop()方法返回top变量指向的元素，
 *           然后将top变量减一，便移除了数据项。要知道 top 变量指向的始终是栈顶的元素。
 *
 * @problem
 *        产生的问题：
 *
 * 　　①、上面栈的实现初始化容量之后，后面是不能进行扩容的（虽然栈不是用来存储大量数据的），如果说后期数据量超过初始容量之后怎么办？（自动扩容）
 *
 * 　　②、我们是用数组实现栈，在定义数组类型的时候，也就规定了存储在栈中的数据类型，那么同一个栈能不能存储不同类型的数据呢？（声明为Object）
 *
 * 　　③、栈需要初始化容量，而且数组实现的栈元素都是连续存储的，那么能不能不初始化容量呢？（改为由链表实现）
 */
public class MyStack {

    private int[] array;

    private int maxSize;

    private int top;

    public MyStack(int size){
        this.maxSize = size;
        array = new int[size];
        top = -1;
    }


    public void push(int value){
        if(top <  maxSize -1){
            array[top++] = value;
        }
    }


    //弹出栈顶数据
    public int pop(){
        return array[top--];
    }

    //访问栈顶数据
    public int peek(){
        return array[top];
    }

    //判断栈是否为空
    public boolean isEmpty(){
        return (top == -1);
    }

    //判断栈是否满了
    public boolean isFull(){
        return (top == maxSize-1);
    }


    public static void main(String[] args) {
        MyStack stack = new MyStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }


    }
}
