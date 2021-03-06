package com.yufeng.leetcode.complexity;

/**
 * @description
 *      递归算法的复杂度分析
 * @author yufeng
 * @create 2019-09-01
 */
public class RecursionTimeComplexity {

    /**
     * 二分查找法
     * 时间复杂度: O(logn)
     */
    private int binarySearch(Comparable[] arr, int l, int r, int target) {
        if (l > r) {
            return  -1;
        }
        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(target) == 0) {
            return mid;
        } else if (arr[mid].compareTo(target) > 0) {
            return binarySearch(arr, l, mid - 1, target);
        } else {
            return binarySearch(arr, mid + 1, r, target);
        }
    }

    /**
     * 递归深度n
     * 时间复杂度: O(n)
     */
    private int sum(int n) {
        assert n >= 0;
        if (n == 0) {
            return 0;
        }
        return n + sum(n - 1);
    }

    /**
     * 递归深度: logn
     * 时间复杂度: O(logn)
     */
    private double pow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double t = pow(x, n/2);
        if (n % 2 == 1) {
            return x * t * t;
        }
        return t * t;
    }

    /**
     * 递归中多次调用, 指数级的算法。
     * 时间复杂度: O(2^n), 非常慢!
     */
    private int f(int n) {
        if (n <= 0) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        RecursionTimeComplexity r = new RecursionTimeComplexity();

        System.out.println(r.sum(100));
        System.out.println(r.pow(2, 10));
        System.out.println(r.f(4));

        Integer[] arr = {3, 5, 7, 10, 8, 9};
        System.out.println(r.binarySearch(arr, 0, arr.length - 1, 5));
    }
}
