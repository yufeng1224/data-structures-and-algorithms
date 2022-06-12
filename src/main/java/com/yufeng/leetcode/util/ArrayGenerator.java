package com.yufeng.leetcode.util;

import java.util.Arrays;
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

    public static Integer[] generateSpecialArray(int n){
        Integer[] arr = new Integer[n];
        generateSpecialArray(arr, 0, arr.length - 1, 0);
        return arr;
    }

    private static void generateSpecialArray(Integer[] arr, int l, int r, int value){
        if (l > r) {
            return;
        }
        // 1. 把最小值放到中间
        int mid = (l + r) / 2;
        arr[mid] = value;

        // 2. 模拟 partition 过程，把中间元素和最左边元素交换位置；
        swap(arr, l, mid);

        // 3. 处理除了最左边的元素之外, 剩下的n-1个元素。 所以, 处理的区间变成了 rr[l+1…r]。同时, 最小值+1
        generateSpecialArray(arr, l + 1, r, value + 1);

        // 4. 都处理好以后，还要把中间的元素和最左边的元素交换回来。
        swap(arr, l, mid);
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

    public static void main(String[] args) {
        Integer[] arr = generateSpecialArray(9);
        System.out.println(Arrays.toString(arr));
    }
}
