package com.notes.study.recursive;

import java.util.Arrays;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/12/3 13:47
 * @Description:
 */
public class Factorial {

    public static void main(String[] args) {
        //System.out.println(getFactorialFor(6));

        //hanoiTower(3,"A","B","C");


        int[] c = {2,7,8,3,1,6,9,0,5,4};
        c = mergeSort(c,0,c.length-1);
        System.out.println(Arrays.toString(c));
    }

    /**
     * for
     * 0！=1  1！=1
     * 负数没有阶乘,如果输入负数返回-1
     * @param n
     * @return
     */
    public static int getFactorialFor(int n) {
        int temp = 1;
        if (n >= 0) {
            for (int i = 1; i <= n; i++) {
                temp = temp * i;
            }
        } else {
            return -1;
        }
        return temp;
    }

    /**
     * 递归
     * 0！=1  1！=1
     * 负数没有阶乘,如果输入负数返回-1
     * @param n
     * @return
     */
    public static int getFactorial(int n){
        if(n >= 0){
            if(n==0){
                System.out.println(n+"!=1");
                return 1;
            }else{
                System.out.println(n);
                int temp = n*getFactorial(n-1);
                System.out.println(n+"!="+temp);
                return temp;
            }
        }
        return -1;
    }

    /**
     * 不用递归的二分查找
     * 找到目标值返回数组下标，找不到返回-1
     * @param array
     * @param key
     * @return
     */
    public static int findTwoPoint(int[] array,int key){
        int start = 0;
        int last = array.length-1;
        while(start <= last){
            int mid = (last-start)/2+start;//防止直接相加造成int范围溢出
            if(key == array[mid]){//查找值等于当前值，返回数组下标
                return mid;
            }
            if(key > array[mid]){//查找值比当前值大
                start = mid+1;
            }
            if(key < array[mid]){//查找值比当前值小
                last = mid-1;
            }
        }
        return -1;
    }

    /**
     * 递归的二分查找
     * 找到目标值返回数组下标，找不到返回-1
     *
     * 递归的二分查找和非递归的二分查找效率都为O(logN)，递归的二分查找更加简洁，便于理解，但是速度会比非递归的慢。
     *
     * @param array
     * @param key
     * @return
     */
    public static int search(int[] array,int key,int low,int high){
        int mid = (high-low)/2+low;
        if(key == array[mid]){//查找值等于当前值，返回数组下标
            return mid;
        }else if(low > high){//找不到查找值，返回-1
            return -1;
        }else{
            if(key < array[mid]){//查找值比当前值小
                return search(array,key,low,mid-1);
            }
            if(key > array[mid]){//查找值比当前值大
                return search(array,key,mid+1,high);
            }
        }
        return -1;
    }


    /**
     * 汉诺塔问题
     * @param dish 盘子个数(也表示名称)
     * @param from 初始塔座
     * @param temp 中介塔座
     * @param to   目标塔座
     */
    public static void hanoiTower(int dish,String from,String temp,String to){
        if(dish == 1){
            System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
        }else{
            hanoiTower(dish-1,from,to,temp);//A为初始塔座，B为目标塔座，C为中介塔座
            System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
            hanoiTower(dish-1,temp,from,to);//B为初始塔座，C为目标塔座，A为中介塔座
        }
    }

    /**
     * 归并算法
     * 传入两个有序数组a和b，返回一个排好序的合并数组
     * @param a
     * @param b
     * @return
     */
    public static int[] sort(int[] a,int[] b){
        int[] c = new int[a.length+b.length];
        int aNum = 0,bNum = 0,cNum=0;
        while(aNum<a.length && bNum < b.length){
            if(a[aNum] >= b[bNum]){//比较a数组和b数组的元素，谁更小将谁赋值到c数组
                c[cNum++] = b[bNum++];
            }else{
                c[cNum++] = a[aNum++];
            }
        }
        //如果a数组全部赋值到c数组了，但是b数组还有元素，则将b数组剩余元素按顺序全部复制到c数组
        while(aNum == a.length && bNum < b.length){
            c[cNum++] = b[bNum++];
        }
        //如果b数组全部赋值到c数组了，但是a数组还有元素，则将a数组剩余元素按顺序全部复制到c数组
        while(bNum == b.length && aNum < a.length){
            c[cNum++] = a[aNum++];
        }
        return c;
    }



    public static int[] mergeSort(int[] c,int start,int last){
        if(last > start){
            //也可以是(start+last)/2，这样写是为了防止数组长度很大造成两者相加超过int范围，导致溢出
            int mid = start + (last - start)/2;

            System.out.println("===="+mid);
            mergeSort(c,start,mid);//左边数组排序
            mergeSort(c,mid+1,last);//右边数组排序
            merge(c,start,mid,last);//合并左右数组
        }
        return c;
    }

    public static void merge(int[] c,int start,int mid,int last){
        int[] temp = new int[last-start+1];//定义临时数组
        int i = start;//定义左边数组的下标
        int j = mid + 1;//定义右边数组的下标
        int k = 0;
        while(i <= mid && j <= last){
            if(c[i] < c[j]){
                temp[k++] = c[i++];
            }else{
                temp[k++] = c[j++];
            }
        }
        //把左边剩余数组元素移入新数组中
        while(i <= mid){
            temp[k++] = c[i++];
        }
        //把右边剩余数组元素移入到新数组中
        while(j <= last){
            temp[k++] = c[j++];
        }

        //把新数组中的数覆盖到c数组中
        for(int k2 = 0 ; k2 < temp.length ; k2++){
            c[k2+start] = temp[k2];
        }
    }


    /**
     * 如果如果求28次方，我们可以先假定22=a,于是28 = （22）4 ，那么就是a4 ；
     * 假定 a2 = b，那么 a4 = b2，而b2可以写成(b2)1。于是现在28就转换成：b*b
     * 也就是说我们将乘方的运算转换为乘法的运算
     * 求xy的值，当y是偶数的时候，最后能转换成两个数相乘，当时当y是奇数的时候，最后我们必须要在返回值后面额外的乘以一个x。
     *
     * @param x
     * @param y
     * @return
     */
    public static int pow(int x,int y){
        if(x == 0 || x == 1){
            return x;
        }
        if(y > 1){
            int b = y/2;
            int a = x*x;
            if(y%2 == 1){//y为奇数
                return pow(a,b)*x;
            }else{//y为偶数
                return pow(a,b);
            }
        }else if(y == 0){
            return 1;
        }else{//y==1
            return x;
        }
    }


}
