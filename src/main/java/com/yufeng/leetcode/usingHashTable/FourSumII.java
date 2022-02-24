package com.yufeng.leetcode.usingHashTable;

import java.util.HashMap;

/**
 * @description
 *      leetcode_454
 * @author yufeng
 * @create 2019-09-17
 */
public class FourSumII {

    public int fourCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || B == null || C == null || D == null) {
            throw new IllegalArgumentException("Illegal argument");
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < C.length; i ++) {
            for (int j = 0; j < D.length; j ++) {
                int sum = C[i] + D[j];
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < A.length; i ++) {
            for (int j = 0; j < B.length; j ++) {
                if (map.containsKey(-A[i]-B[j])) {
                    res += map.get(-A[i]-B[j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 2};
        int[] b = {-2, -1};
        int[] c = {-1, 2};
        int[] d = {0, 2};

        FourSumII f = new FourSumII();
        System.out.println(f.fourCount(a, b, c, d));
    }
}
