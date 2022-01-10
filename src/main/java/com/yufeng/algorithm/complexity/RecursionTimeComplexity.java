package com.yufeng.algorithm.complexity;

/**
 * @description
 *      递归算法的复杂度分析
 * @author yufeng
 * @create 2019-09-01
 */
public class RecursionTimeComplexity {

    /**
     * 二分查找法
     * 时间复杂度O(logN)
     */
    private static int binarySearch(Comparable[] arr, int l, int r, int target) {
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
     * 递归深度n, 时间复杂度O(n)
     */
    private static int sum(int n) {
        assert n >= 0;
        if (n == 0) {
            return 0;
        }
        return n + sum(n - 1);
    }

    /**
     * 递归深度logN, 时间复杂度O(logN)
     */
    private static double pow(double x, int n) {
        assert n >= 0;
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
     * 递归中多次调用。指数级的算法, O(2^n)
     * 非常慢!
     */
    private static int f(int n) {
        assert (n >= 0);

        if (n == 0) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(sum(100));
        System.out.println(pow(2, 10));

        System.out.println(f(10));
    }
}
