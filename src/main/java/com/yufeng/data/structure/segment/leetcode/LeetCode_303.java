package com.yufeng.data.structure.segment.leetcode;

import com.yufeng.data.structure.segment.SegmentTree;

/**
 * 区域和检索 - 数组不可变
 *    给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 *
 *    实现 NumArray 类：
 *    NumArray(int[] nums) 使用数组 nums 初始化对象
 *     int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是
 *     sum(nums[i], nums[i + 1], ... , nums[j])）
 *
 *
 * 示例：
 * 输入：
 *      ["NumArray", "sumRange", "sumRange", "sumRange"]
 *      [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 *      输出：
 *      [null, 1, -1, -3]
 *
 * 解释：
 *      NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 *      numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 *      numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 *      numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 *
 *
 * 提示：
 *      0 <= nums.length <= 104
 *      -105 <= nums[i] <= 105
 *      0 <= i <= j < nums.length
 *      最多调用 104 次 sumRange 方法
 */
public class LeetCode_303 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}


/**
 * 使用线段树的方式设计 NumArray 类
 *
 * 执行结果：通过 显示详情
 * 执行用时：18 ms, 在所有 Java 提交中击败了30.02%的用户
 * 内存消耗：43.2 MB, 在所有 Java 提交中击败了5.04%的用户
 *
 */
class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i ++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }


    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }
        return segmentTree.query(i, j);
    }
}


class NumArray2 {

    private int[] sum;          // sum[i] 存储前i个元素和, 比如 sum[0] = 0， sum[i] 存储 nums[0...i-1] 的和

    public NumArray2(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i ++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }


    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

}











