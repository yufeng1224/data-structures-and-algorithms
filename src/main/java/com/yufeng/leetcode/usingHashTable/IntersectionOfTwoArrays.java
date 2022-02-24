package com.yufeng.leetcode.usingHashTable;

import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @description
 *      leetcode_349
 * @author yufeng
 * @create 2019-09-17
 */
public class IntersectionOfTwoArrays {

    /**
     * 时间复杂度: O(nlogn), 空间复杂度: O(n)
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        TreeSet<Integer> record = new TreeSet<>();
        for (int num : nums1) {
            record.add(num);
        }

        TreeSet<Integer> resultSet = new TreeSet<>();
        for (int num : nums2) {
            if (record.contains(num)) {
                resultSet.add(num);
            }
        }

        int[] res = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            res[index ++] = num;
        }
        return res;
    }

    /**
     * 时间复杂度: O(len(nums1)) + O(len(nums2)), 空间复杂度: O(len(nums1))
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> record = new HashSet<>();
        for (int num : nums1) {
            record.add(num);
        }

        HashSet<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < nums2.length; i ++) {
            if (record.contains(nums2[i])) {
                resultSet.add(nums2[i]);
            }
        }

        int[] res = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            res[index ++] = num;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] nums3 = {1, 2, 2, 1};
        int[] nums4 = {2, 2};

        IntersectionOfTwoArrays intersectionOfTwoArrays = new IntersectionOfTwoArrays();
        int[] res = intersectionOfTwoArrays.intersection1(nums1, nums2);
        ArrayGenerator.printArr(res);

        int[] res2 = intersectionOfTwoArrays.intersection2(nums3, nums4);
        ArrayGenerator.printArr(res2);
    }

}
