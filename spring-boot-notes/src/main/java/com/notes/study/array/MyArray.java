package com.notes.study.array;

import java.awt.geom.Ellipse2D;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 11:06
 * @Description:
 */
public class MyArray {

    private int[] intArray;

    private int length;

    private int elems;

    public MyArray(){
        intArray = new int[10];
        length = 50;
        elems = 0;
    }

    public MyArray(int size){
        intArray = new int[size];
        length = size;
        elems = 0;
    }

    public int getSize(){
        return elems;
    }

    public boolean add(int value){
        if(elems == length ){
            return false;
        }

        intArray[elems] = value;
        elems++;
        return true;
    }

    public int get(int i){
       if(i < 0 || i > elems){
           System.out.println("");
       }
        return intArray[i];
    }

    public int find(int value){
        if(elems == 0){
            return -1;
        }
        int i ;
        for(i = 0; i < elems; i++){
            if(intArray[i] == value){
                break;
            }
        }
        return i;
    }
}
