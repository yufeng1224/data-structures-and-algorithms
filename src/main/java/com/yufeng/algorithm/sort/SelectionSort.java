package com.yufeng.algorithm.sort;

import com.yufeng.leetcode.util.ArrayGenerator;

/**
 * @description
 *      选择排序
 * @author yufeng
 * @create 2019-10-12
 */
public class SelectionSort {

    private SelectionSort(){}

    /** 选择排序时间复杂度: O(n^2) */

    /**
     * 原地排序: 实现方式1
     */
    public static <E extends Comparable<E>> void sort1(E[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j ++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 原地排序: 实现方式2
     * 循环不变量: arr[i...n)已排序, arr[0...i)未排序
     */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = arr.length - 1; i >= 0; i --) {
            int maxIndex = i;
            for (int j = i; j >= 0; j --) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(arr, i, maxIndex);
        }
    }

    /**
     * 原地排序: 实现方式3
     */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int i = 0; i < arr.length - 1; i ++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j ++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 4, 2, 3, 6, 5};
        Integer[] arr2 = {1, 4, 2, 3, 6, 5};
        Integer[] arr3 = {1, 4, 2, 3, 6, 5};

        SelectionSort.sort1(arr1);
        SelectionSort.sort2(arr2);
        SelectionSort.sort3(arr3);

        for (int e : arr1) {
            System.out.print(e + " ");
        }
        System.out.println();

        for (int e : arr2) {
            System.out.print(e + " ");
        }
        System.out.println();

        for (int e : arr3) {
            System.out.print(e + " ");
        }
        System.out.println();

        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort", arr);
        }
    }
}
