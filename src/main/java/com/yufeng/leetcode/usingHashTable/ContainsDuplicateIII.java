package com.yufeng.leetcode.usingHashTable;

import java.util.TreeSet;

/**
 * @description
 *      leetcode_220
 * @author yufeng
 * @create 2019-09-17
 */
public class ContainsDuplicateIII {

    /**
     * 时间复杂度: O(nlogk), 空间复杂度: O(k)
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 这个问题的测试数据在使用int进行加减运算时会溢出, 因此使用long类型
        TreeSet<Long> record = new TreeSet<>();
        for (int i = 0; i < nums.length; i ++) {
            if (record.ceiling((long)nums[i] - (long)t) != null &&
                    record.ceiling((long)nums[i] - (long)t) <= (long)nums[i] + (long)t) {
                return true;
            }

            record.add((long)nums[i]);
            if (record.size() == k + 1) {
                record.remove((long)nums[i - k]);
            }
        }
        return false;
    }

    private static void printBool(boolean b) {
        System.out.println(b ? "True" : "False");
    }

    public static void main(String[] args) {
        int[] nums = {-2147483648, -2147483647};
        int k = 3;
        int t = 3;
        printBool(new ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, k, t));
    }

}
