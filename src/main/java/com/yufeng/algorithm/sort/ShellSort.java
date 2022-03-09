package com.yufeng.algorithm.sort;

import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.Arrays;

/**
 * @description
 *      希尔排序
 * @author yufeng
 * @create 2019-10-25
 */
public class ShellSort {

    private ShellSort() {}

    public static <E extends Comparable<E>> void sort(E[] data) {
        int h = data.length / 2;

        while (h >= 1) {

            for (int start = 0; start < h; start ++) {

                // 对data[start, start + h, start + 2h, start + 3h ...], 进行插入排序
                for (int i = start + h; i < data.length; i += h) {

                    E t = data[i];
                    int j;
                    for (j = i; j - h >= 0 && t.compareTo(data[j - h]) < 0; j -= h) {
                        data[j] = data[j - h];
                    }
                    data[j] = t;
                }
            }

            h /= 2;
        }
    }

    /**
     * 希尔排序内层循环代码简化
     */
    public static <E extends Comparable<E>> void sort2(E[] data) {
        int h = data.length / 2;

        while (h >= 1) {
            /** 对 data[h, n) 进行插入排序 */
            for (int i = h; i < data.length; i ++) {

                int j;
                E t = data[i];
                for (j = i; j - h >= 0 && t.compareTo(data[j - h]) < 0; j -= h) {
                    data[j] = data[j - h];
                }
                data[j] = t;
            }
            h /= 2;
        }
    }

    /**
     * 使用新的步长序列实现希尔排序
     */
    public static <E extends Comparable<E>> void sort3(E[] data) {
        int h = 1;

        while (h < data.length) {
            h = h * 3 + 1;              // 1, 4, 13, 40...
        }

        while (h >= 1) {
            /** 对 data[h, n) 进行插入排序 */
            for (int i = h; i < data.length; i ++) {

                E t = data[i];
                int j;
                for (j = i; j - h >= 0 && t.compareTo(data[j - h]) < 0; j = j - h) {
                    data[j] = data[j - h];
                }
                data[j] = t;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        /** 排序性能比较: 希尔排序、插入排序、 归并排序*/
        int n = 100000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("ShellSort", arr);
        SortingHelper.sortTest("InsertionSort", arr2);
        SortingHelper.sortTest("MergeSort3", arr3);
    }
}

























