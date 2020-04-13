package com.notes.study.sort;

import java.util.Arrays;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2020/4/13 16:04
 * @Description: 希尔排序
 * 　希尔排序通过加大插入排序中元素的间隔，并在这些有间隔的元素中进行插入排序，从而使数据项能够大跨度的移动。
 * 当这些数据项排过一趟序后，希尔排序算法减小数据项的间隔再进行排序，依次进行下去，最后间隔为1时，
 * 就是我们上面说的简单的直接插入排序。
 */
public class KnuthSort {

    //希尔排序 knuth 间隔序列 3h+1
    public static void shellKnuthSort(int[] array) {
        System.out.println("原数组为" + Arrays.toString(array));
        int step = 1;
        int len = array.length;
        while (step <= len / 3) {
            step = step * 3 + 1;//1,4,13,40......
        }
        while (step > 0) {
            //分别对每个增量间隔进行排序
            for (int i = step; i < len; i++) {
                int temp = array[i];
                int j = i;
                while (j > step - 1 && temp <= array[j - step]) {
                    array[j] = array[j - step];
                    j -= step;
                }
                array[j] = temp;
            }//end for
            System.out.println("间隔为" + step + "的排序结果为" + Arrays.toString(array));
            step = (step - 1) / 3;
        }//end while(step>0)

        System.out.println("最终排序：" + Arrays.toString(array));

    }


    //希尔排序 间隔序列2h
    public static void shellSort(int[] array){
        System.out.println("原数组为"+Arrays.toString(array));
        int step;
        int len = array.length;
        for(step = len/2 ;step > 0 ; step /= 2){
            //分别对每个增量间隔进行排序
            for(int i = step ; i < array.length ; i++){
                int j = i;
                int temp = array[j];
                if(array[j] < array[j-step]){
                    while(j-step >=0 && temp < array[j-step]){
                        array[j] = array[j-step];
                        j -= step;
                    }
                    array[j] = temp;
                }
            }
            System.out.println("间隔为"+step+"的排序结果为"+Arrays.toString(array));
        }
    }


    public static void main(String[] args) {
        int[] array = {4,2,8,9,5,7,6,1,3,10};
        shellKnuthSort(array);
    }
}
