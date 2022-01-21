package com.yufeng.leetcode.complexity;

import com.yufeng.leetcode.util.MyUtil;

/**
 * @description
 *      1. 复杂度实验
 *      2. 数据规模每次增加2倍, 观察不同算法时间复杂度的变化
 * @author yufeng
 * @create 2019-09-01
 */
public class TimeComplexityExperiments {

    /**
     * O(n)时间复杂度实验
     */
    public static void testFindMax() {
        System.out.println("Test for findMax: ");

        for (int i = 10; i <= 26; i ++) {
            int n = (int)Math.pow(2, i);
            Integer[] arr = MyUtil.generateRandomArray(n, 0, 100000000);

            long startTime = System.currentTimeMillis();
            findMax(arr, n);
            long endTime = System.currentTimeMillis();

            System.out.print("data size 2^" + i + " = " + n + "\t");
            System.out.println("Time cost: " + (endTime - startTime) + " ms");
        }
    }

    private static Integer findMax(Integer[] arr, int n) {
        assert n > 0;

        Integer res = arr[0];
        for (int i = 0; i < n; i++) {
            if (arr[i] > res) {
                res = arr[i];
            }
        }
        return res;
    }

    /**
     * O(n^2)时间复杂度实验
     */
    public static void testSelectionSort() {
        System.out.println("Test for Selection Sort: ");

        for (int i = 10; i <= 15; i ++) {
            int n = (int)Math.pow(2, i);
            Integer[] arr = MyUtil.generateRandomArray(n, 0, 100000000);

            long startTime = System.currentTimeMillis();
            new CommonCodeTimeComplexity().selectionSort(arr, n);
            long endTime = System.currentTimeMillis();

            System.out.print("data size: 2^" + i + " = " + n + "\t");
            System.out.println("Time cost: " + (endTime - startTime) + " ms");
        }
    }

    /**
     * O(logn)时间复杂度实验
     */
    public static void testBinarySearch() {
        System.out.println("Test for Binary Search: ");

        for (int i = 20; i <= 28 ; i++) {
            int n = (int)Math.pow(2, i);
            Integer[] arr = MyUtil.generateOrderArray(n);

            long startTime = System.currentTimeMillis();
            new CommonCodeTimeComplexity().binarySearch(arr, n, 0);
            long endTime = System.currentTimeMillis();

            System.out.print("data size 2^" + i + " = " + n + "\t");
            System.out.println("Time cost: " + (endTime - startTime) + " ms");
        }
    }

    /**
     * O(nlogn)时间复杂度实验
     */
    public static void testMergeSort() {
        System.out.println("Test for Merge Sort: ");

        for (int i = 20; i <= 26 ; i++) {
            int n = (int)Math.pow(2, i);
            Integer[] arr = MyUtil.generateRandomArray(n, 0, 1<<30);

            long startTime = System.currentTimeMillis();
            mergeSort(arr, n);
            long endTime = System.currentTimeMillis();

            System.out.print("data size 2^" + i + " = " + n + "\t");
            System.out.println("Time cost: " + (endTime - startTime) + " ms");
        }
    }

    private static void mergeSort(Comparable[] arr, int n) {
        Comparable[] aux = new Comparable[n];
        for (int i = 0; i < n; i ++) {
            aux[i] = arr[i];
        }

        for (int sz = 1; sz < n; sz += sz) {
            for (int i = 0; i < n; i += sz + sz) {
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), aux);
            }
        }
        return;
    }

    private static void merge(Comparable[] arr, int l, int mid, int r, Comparable[] aux) {
        for (int i = l; i <= r; i ++) {
            aux[i] = arr[i];
        }

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k ++) {
            if (i > mid) {
                arr[k] = aux[j];
                j ++;
            } else if (j > r) {
                arr[k] = aux[i];
                i ++;
            } else if (aux[i].compareTo(arr[j]) < 0) {
                arr[k] = aux[i];
                i ++;
            } else {
                arr[j] = aux[j];
                j ++;
            }
        }
    }

    public static void main(String[] args) {
        testFindMax();
        testSelectionSort();
        testBinarySearch();
        testMergeSort();
    }
}
