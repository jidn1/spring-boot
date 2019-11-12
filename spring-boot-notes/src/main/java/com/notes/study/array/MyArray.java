package com.notes.study.array;

import java.awt.geom.Ellipse2D;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 11:06
 * @Description:
 *
 * 数组的局限性分析：
 *
 * 　　①、插入快，对于无序数组，上面我们实现的数组就是无序的，即元素没有按照从大到小或者某个特定的顺序排列，只是按照插入的顺序排列。无序数组增加一个元素很简单，只需要在数组末尾添加元素即可，但是有序数组却不一定了，它需要在指定的位置插入。
 *
 * 　　②、查找慢，当然如果根据下标来查找是很快的。但是通常我们都是根据元素值来查找，给定一个元素值，对于无序数组，我们需要从数组第一个元素开始遍历，直到找到那个元素。有序数组通过特定的算法查找的速度会比无需数组快，后面我们会讲各种排序算法。
 *
 * 　　③、删除慢，根据元素值删除，我们要先找到该元素所处的位置，然后将元素后面的值整体向前面移动一个位置。也需要比较多的时间。
 *
 * 　　④、数组一旦创建后，大小就固定了，不能动态扩展数组的元素个数。如果初始化你给一个很大的数组大小，那会白白浪费内存空间，如果给小了，后面数据个数增加了又添加不进去了。
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
