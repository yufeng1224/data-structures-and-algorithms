package com.yufeng.leetcode.util;

import java.util.Random;

/**
 * @description
 *      数组生成工具
 * @author yufeng
 * @create 2019-09-01
 */
public class ArrayGenerator {

    private ArrayGenerator() {}

    /**
     * 生成随机数组
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = (int)(Math.random() * (rangeR - rangeL + 1)) + rangeL;
        }
        return arr;
    }

    /**
     * 生成一个长度为n的随机数组, 每个数字的范围是[0, bound)
     */
    public static Integer[] generateRandomArray(int n, int bound) {
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i ++) {
            arr[i] = random.nextInt(bound);
        }
        return arr;
    }

    /**
     * 生成有序数组
     */
    public static Integer[] generateOrderArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * 打印数组
     */
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 数据交换
     */
    public static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
