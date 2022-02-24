package com.yufeng.leetcode.usingHashTable;

import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @description
 *      leetcode_350
 * @author yufeng
 * @create 2019-09-17
 */
public class IntersectionOfTwoArraysII {

    public int[] interset(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> record = new TreeMap<>();
        for (int num : nums1) {
            if (!record.containsKey(num)) {
                record.put(num, 1);
            } else {
                record.put(num, record.get(num) + 1);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (record.containsKey(num) && record.get(num) > 0) {
                result.add(num);
                record.put(num, record.get(num) + 1);
            }
        }

        int[] ret = new int[result.size()];
        int index = 0;
        for (Integer num : result) {
            ret[index ++] = num;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] res = new IntersectionOfTwoArraysII().interset(nums1, nums2);
        ArrayGenerator.printArr(res);
    }

}
