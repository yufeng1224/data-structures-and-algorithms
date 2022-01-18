package com.yufeng.algorithm.usingArray;

/**
 * @description
 *      1. leetcode 209解题分析
 *      2. 深刻理解双索引技术
 * @author yufeng
 * @create 2019-09-04
 */
public class MinimumSizeSubarraySum {

    /**
     * 1. 暴露解法: 该方法在leetcode中会超时
     * 2. 时间复杂度: O(n^3); 空间复杂度: O(1)
     * 3. 缺陷: 大量重复的计算
     */
    public int minSubArrayLen1(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Arguments");
        }
        int res = nums.length + 1;
        for (int l = 0; l < nums.length; l ++) {
            for (int r = l; r < nums.length; r ++) {
                int sum = 0;
                for (int i = l; i <= r; i ++) {
                    sum += nums[i];
                    if (sum >= s) {
                        res = Math.min(res, r - l + 1);
                    }
                }
            }
        }

        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }

    /**
     * 1. 优化暴力破解
     * 2. 时间复杂度: O(n^2); 空间复杂度: O(n)
     */
    public int minSubArrayLen2(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Arguments");
        }
        int res = nums.length + 1;

        int[] sums = new int[nums.length + 1];          // sum[i]存放[0, nums[0]... nums[0..length-1]]的和
        for (int i = 1; i <= nums.length; i ++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int l = 0; l < nums.length; l ++) {
            for (int r = l; r < nums.length; r ++) {
                if (sums[r + 1] - sums[l] >= s) {       // 使用sum[r+1] - sum[l] 快速获得nums[l...r]的和
                    res = Math.min(res, r - l + 1);
                }
            }
        }

        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }

    /**
     * 1. 滑动窗口思路
     * 2. 时间复杂度: O(n); 空间复杂度: O(1)
     */
    public int minSubArrayLen3(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Arguments");
        }
        int res = nums.length + 1;

        int l = 0, r = -1;                              // nums[l...r]为滑动窗口, 初始时窗口不包含任何元素, 故r=-1
        int sum = 0;
        while (l < nums.length) {                       // 窗口的左边界在数组范围内, 则循环继续
            if (r + 1 < nums.length && sum < s) {       // 窗口的右边界可以继续向右滑动并且sum小于目标值, 可以继续求和
                r ++;
                sum += nums[r];
            } else {                                    // 窗口的右边界已经到头或者sum大于目标值, 减去最左端的元素并缩小左边界
                sum -= nums[l];
                l ++;
            }

            if (sum >= s) {                             // 动态滑动窗口的过程中, 当sum大于目标值, 记录当前窗口的区间长度
                res = Math.min(res, r - l + 1);
            }
        }

        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }

    /**
     * 1. 滑动窗口实现思路(二)
     * 2. 时间复杂度: O(n); 空间复杂度: O(1)
     */
    public int minSubArrayLen4(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Arguments");
        }
        int res = nums.length + 1;

        int l = 0, r = -1;
        int sum = 0;
        while (r + 1 < nums.length) {
            while (r + 1 < nums.length && sum < s) {    // 当sum小于目标值时, 右边界一直向右滑动
                r ++;
                sum += nums[r];
            }

            if (sum >= s) {                             // 当sum大于目标值时, 记录当前窗口的区间长度
                res = Math.min(res, r - l + 1);
            }

            while (l < nums.length && sum >= s) {       // 上面统计完之后, 右边界暂停滑动。 左边界缩小, 向右滑动
                sum -= nums[l];
                l ++;
                if (sum >= s) {
                    res = Math.min(res, r - l + 1);
                }
            }
        }

        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }

    /**
     * 1. 二分搜索应用
     * 2. 时间复杂度: O(nlogn); 空间复杂度: O(n)
     */
    public int minSubArrayLen5(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Arguments");
        }

        int[] sums = new int[nums.length + 1];          // sum[i]存放[0, nums[0]... nums[0..length-1]]的和
        for (int i = 1; i <= nums.length; i ++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int res = nums.length + 1;
        for (int l = 0; l < nums.length; l ++) {
            // Java类库中没有内置的lowerBound方法, 自定义实现
            int r = lowerBound(sums, sums[l] + s);  // 寻找离l位置最近的右边界r, 并计算区间长度
            if (r != sums.length) {
                res = Math.min(res, r - l);
            }
        }

        if (res == nums.length + 1) {
            return 0;
        }
        return res;
    }

    /**
     * 在有序数组中寻找大于等于target的最小值
     * 如果没有(nums数组中所有值都小于target), 则返回nums.length
     */
    private int lowerBound(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Illegal argument nums is lowerBound");
        }

        int l = 0, r = nums.length;
        while (l != r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }



    public static void main(String[] args) {
        MinimumSizeSubarraySum m = new MinimumSizeSubarraySum();

        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

        System.out.println(m.minSubArrayLen1(target, nums));
        System.out.println(m.minSubArrayLen2(target, nums));
        System.out.println(m.minSubArrayLen3(target, nums));
        System.out.println(m.minSubArrayLen4(target, nums));
        System.out.println(m.minSubArrayLen5(target, nums));
    }

}
