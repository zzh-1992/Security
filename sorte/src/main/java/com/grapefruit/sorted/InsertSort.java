package com.grapefruit.sorted;

import java.util.Arrays;
/**
 * 插入排序
 *
 * @since 2020-11-13
 */
public class InsertSort {

    public static void main(String[] args) {
        // 原始数组
        int[] i = {1,3,4,5,2};

        sort(i);
        Arrays.stream(i).forEach(System.out::println);
    }

    /**
     * 原始数组1 3,4,5 2
     * 第一次变换 从下标1开始(第二个元素开始比较,当该元素比前面的元素小时,进行元素移动操作)
     *  k=4, a[j] = 2, 用2与数组中的元素依次比较
     *  1 2 3,4,5 (元素3,4,5遍历往后移动一位,之后再把"a[j] = 2"替换到切入点)
     *
     */

    public static void sort(int[] array){
        for(int k = 1;k< array.length;k++){
            // 升序排列
            for(int j = 0;j < k;j++){
                int tmp = array[k];
                // 如果后面的元素小于前一个元素,开始移动数据,并把元素替换到该位置
                if(tmp < array[j]){
                    //移动元素
                    for(int m = k;m > j;m--){
                        array[m]=array[m-1];
                    }
                    array[j] = tmp;
                }
            }
        }
    }
}
