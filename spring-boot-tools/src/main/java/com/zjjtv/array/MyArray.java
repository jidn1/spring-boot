package com.zjjtv.array;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/6 17:01
 * @Description:
 */
public class MyArray {

    //定义一个数组
    private int[] intArray;
    //数组实际有效长度
    private int elems;
    //数组长度
    private int length;

    public MyArray() {
        elems = 0;
        length = 50;
        intArray = new int[length];
    }

    public MyArray(int length) {
        elems = 0;
        this.length = length;
        intArray = new int[length];
    }

    public int getSize() {
        return elems;
    }

    public void display() {
        for (int i = 0; i < elems; i++) {
            System.out.println(intArray[i] + " ");
        }
        System.out.println();
    }

    public boolean add(int value) {
        if (elems == length) {
            return false;
        } else {
            intArray[elems] = value;
            elems++;
        }
        return true;
    }

    public int get(int i){
        if(i < 0 || i> elems){
            System.out.println("");
        }
        return intArray[i];
    }


    public int find(int value){
        int i;
        for(i = 0; i< elems; i++){
            if(intArray[i] == value){
                break;
            }
        }

        if(i == elems){
            return -1;
        }
        return i;
    }


    public boolean delete(int value){
        int i = find(value);
        if(i == -1){
            return false;
        } else {
            if(i == elems -1){
                elems --;
            } else {
                for(int j = i; j< elems -1;j++){
                    intArray[j] = intArray[j+1];
                }
                elems --;
            }
            return true;
        }
    }

    public  boolean modify(int oldValue,int newValue){
        int i = find(oldValue);
        if(i == -1){
            return false;
        } else {
            intArray[i] = newValue;
            return true;
        }
    }


    public static void main(String[] args) {
        //创建自定义封装数组结构，数组大小为4
        MyArray array = new MyArray(4);
        //添加4个元素分别是1,2,3,4
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        //显示数组元素
        array.display();
        //根据下标为0的元素
        int i = array.get(0);
        System.out.println(i);
        //删除4的元素
        array.delete(4);
        //将元素3修改为33
        array.modify(3, 33);
        array.display();
    }
}
