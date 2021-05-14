package com.yufeng.data.structure.recursion;

/**
 * 描述:
 *      通过递归求和
 * @author yufeng
 * @create 2019-08-01
 */
public class RecursiveSum {

    public  static  int  sum(int[] arr) {
        return sum(arr, 0);
    }


    // 计算 arr[l ... n) 这个区间内所有数字的和
    // 递归函数就是一个函数, 完成一个功能
    private static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;                               // 求解最基本问题
        }
        return arr[l] + sum(arr, l + 1);          // 把原问题转化成更小的问题
    }


    // 递归函数的调用, 本质就是函数调用
    private static int sum2(int[] arr, int l) {
        if (l == arr.length) {
            return 0;                               // 求解最基本问题
        }

        int x = sum2(arr, l+1);
        int res = arr[l] + x;
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(sum(nums));
    }

}
