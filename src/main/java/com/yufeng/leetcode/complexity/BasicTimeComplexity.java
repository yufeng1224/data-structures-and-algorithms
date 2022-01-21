package com.yufeng.leetcode.complexity;

/**
 * @description
 *      数据规模与时间复杂度演示
 * @author yufeng
 * @create 2019-09-01
 */
public class BasicTimeComplexity {

    /**
     * 数据规模每次增大10倍, 时间消耗演示
     */
    public void testTime() {
        for (int i = 1; i <= 9; i ++) {
            int n = (int)Math.pow(10, i);

            long startTime = System.currentTimeMillis();
            long sum = 0;
            for (int j = 0; j < n; j ++) {
                sum += j;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("10^" + i + ":" + (endTime - startTime) + " ms");
            System.out.println("sum = " + sum);
            System.out.println();
        }
    }

    /** 空间复杂度: 递归调用是有空间代价的。存在栈内存溢出的风险 */
    /**
     * 空间复杂度: O(1)
     */
    private int sum1(int n) {
        if (n <= 0) {
            return 0;
        }
        int ret = 0;
        for (int i = 0; i <= n; i ++) {
            ret += i;
        }
        return ret;
    }

    /**
     * 空间复杂度: O(n)
     */
    private int sum2(int n) {
        if (n == 0) {
            return 0;
        }
        return n + sum2(n - 1);
    }

    public static void main(String[] args) {
        BasicTimeComplexity basicTimeComplexity = new BasicTimeComplexity();
        basicTimeComplexity.testTime();
        System.out.println(basicTimeComplexity.sum1(10000));
        System.out.println(basicTimeComplexity.sum2(10000));        // overflow
    }
}
