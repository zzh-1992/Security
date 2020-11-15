package com.grapefruit.sorted;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @since 2020-11-13
 */
public class ChoseSort {

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
        for(int k = 0;k< array.length;k++){
            // 先假设第一个元素是最小值
            int minIndex = k;
            int min = array[k];
            for(int j = k;j < array.length;j++){
                // 遍历最小值
                if(min > array[j]){
                    //获取最小zhi
                    min = array[j];
                    minIndex = j;
                }
            }
            // 如果改次循环的最小值与初始值不相等,让改次循环的元素与最小值处的元素互换位置
            if(min != array[k]){
                int tmp = array[k];
                array[k] = min;
                array[minIndex] = tmp;
            }

        }
    }
}
