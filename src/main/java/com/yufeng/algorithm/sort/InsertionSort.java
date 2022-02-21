package com.yufeng.algorithm.sort;

import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.Arrays;

/**
 * @description
 *      插入排序
 * @author yufeng
 * @create 2019-10-12
 */
public class InsertionSort {

    private InsertionSort(){}

    /** 插入排序时间复杂度: O(n^2) */

    /**
     * 实现方式1
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            // 将arr[i]插入到合适的位置
            for (int j = i; j - 1 >= 0; j --) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                } else {                        // 都排好序了
                    break;
                }
            }
        }
    }

    /** 代码进一步精简 */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            for (int j = i; j - 1 >= 0 && arr[j].compareTo(arr[j - 1]) < 0; j --) {
                swap(arr, j, j - 1);
            }
        }
    }

    /**
     * 实现方式2
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            E temp = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && temp.compareTo(arr[j - 1]) < 0; j --) {
                arr[j] = arr[j - 1];            // 数据交换改为赋值操作
            }
            arr[j] = temp;
        }
    }

    /**
     * 1. 从后往前实现插入排序法
     * 2. arr[0, i) 未排序, arr[i, n)已排好序
     */
    public static <E extends Comparable<E>> void sort4(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i --) {
            E temp = arr[i];
            int j;
            for (j = i; j + 1 < arr.length && temp.compareTo(arr[j + 1])> 0; j ++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = temp;
        }
    }

    /**
     * 对于区间中的数组进行排序
     */
    public static <E extends Comparable<E>> void sort5(E[] arr, int l, int r) {
        for (int i = l; i <= r; i ++) {
            E temp = arr[i];
            int j;
            for (j = i; j - 1 >= l && temp.compareTo(arr[j - 1]) < 0; j --) {
                arr[j] = arr[j - 1];            // 数据交换改为赋值操作
            }
            arr[j] = temp;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 4, 2, 3, 6, 5};
        InsertionSort.sort1(arr1);
        for (int i = 0; i < arr1.length; i ++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        Integer[] arr2 = {1, 4, 2, 3, 6, 5};
        InsertionSort.sort2(arr2);
        for (int j = 0; j < arr2.length; j ++) {
            System.out.print(arr2[j] + " ");
        }
        System.out.println();


        /** 选择排序和插入排序比较 */
        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            System.out.println("Random Array : ");
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arrCopy = Arrays.copyOf(arr, arr.length);

            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("SelectionSort", arrCopy);
            System.out.println();

            System.out.println("Ordered Array : ");
            arr = ArrayGenerator.generateOrderArray(n);
            arrCopy = Arrays.copyOf(arr, arr.length);

            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("SelectionSort", arrCopy);
            System.out.println();
        }
    }
}
