package com.yufeng.leetcode.util;

/**
 * @description
 *      数组生成工具
 * @author yufeng
 * @create 2019-09-01
 */
public class MyUtil {

    private MyUtil() {}

    /**
     * 生成随机数组
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert n > 0 && rangeL <= rangeR;

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = (int)(Math.random() * (rangeR - rangeL + 1)) + rangeL;
        }
        return arr;
    }

    /**
     * 生成有序数组
     */
    public static Integer[] generateOrderArray(int n) {
        assert n > 0;

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i ++) {
            arr[i] = i;
        }
        return arr;
    }
}
