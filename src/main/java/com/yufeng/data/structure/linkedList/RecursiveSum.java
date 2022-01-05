package com.yufeng.data.structure.linkedList;

/**
 * @description
 *      递归案例演示
 * @author yufeng
 * @create 2019-07-10
 */
public class RecursiveSum {

    public  static  int  sum(int[] arr) {
        return sum(arr, 0);
    }

    /**
     * 计算 arr[l ... n) 区间内所有数字的和
     */
    private static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;                               // 求解最基本问题
        }
        return arr[l] + sum(arr, l + 1);          // 把原问题转化成更小的问题
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(sum(nums));
    }

}
