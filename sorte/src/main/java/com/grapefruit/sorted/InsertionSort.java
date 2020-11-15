package com.grapefruit.sorted;


import java.util.Arrays;

/**
 * 插入排序(数据与结构算法分析 P273)
 * @author book
 * @since 2020-10-28
 */
public class InsertionSort {

    public static void main(String[] args)  {
        int[] i = {2,1,3,7,39,0};
        is(i);
        Arrays.stream(i).forEach(System.out::println);

    }
    public static <AnyType extends Comparable<?super AnyType>> void insertionSort(AnyType[] a){
        int j;
        for (int p = 1; p < a.length ; p++) {
            AnyType tmp = a[p];
            for (j = p; j > 0 && tmp.compareTo(a[j-1]) < 0;j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }

    public static void is(int[] a){
        int j;
        for (int p = 1; p < a.length ; p++) {
            int tmp = a[p];
            for (j = p; j > 0 && (tmp - a[j-1]) < 0;j--) {
                a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }
}
