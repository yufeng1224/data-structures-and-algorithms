package com.yufeng.leetcode.usingHashTable;

import java.util.HashMap;

/**
 * @description
 *      leetcode_447
 * @author yufeng
 * @create 2019-09-17
 */
public class NumberOfBoomerangs {

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i ++) {
            // record 中存储点i到所有其他点的距离出现的频次
            HashMap<Integer, Integer> record = new HashMap<>();

            for (int j = 0; j < points.length; j ++) {
                if (j != i) {
                    // 计算距离时不进行根运算, 以保证精度
                    int dis = dis(points[i], points[j]);
                    if (record.containsKey(dis)) {
                        record.put(dis, record.get(dis) + 1);
                    } else {
                        record.put(dis, 1);
                    }
                }
            }

            for (Integer dis : record.keySet()) {
                res += record.get(dis) * (record.get(dis) - 1);
            }
        }
        return res;
    }

    private int dis(int[] pa, int[] pb) {
        return (pa[0] - pb[0]) * (pa[0] - pb[0]) +
                (pa[1] - pb[1]) * (pa[1] - pb[1]);
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        NumberOfBoomerangs n = new NumberOfBoomerangs();
        System.out.println(n.numberOfBoomerangs(points));
    }
}
