package com.yufeng.leetcode.usingArray;

import java.util.ArrayList;

/**
 * @description
 *      1. leetCode-283
 *      2. 数组应用
 * @author yufeng
 * @create 2019-09-03
 */
public class MoveZeroes {

    /**
     * 时间复杂度O(n), 空间复杂度O(n)
     */
    public void moveZeroes1(int[] nums) {
        ArrayList<Integer> nonZeroElements = new ArrayList<>();

        /** 将数组中所有非0元素放入nonZeroElements */
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeroElements.add(nums[i]);
            }
        }

        /** 将nonZeroElements中的所有元素依次放入到nums开始的位置 */
        for (int i = 0; i < nonZeroElements.size(); i ++) {
            nums[i] = nonZeroElements.get(i);
        }

        /** 将nums剩余的位置放置为0 */
        for (int i = nonZeroElements.size(); i < nums.length; i ++) {
            nums[i] = 0;
        }
    }

    /**
     * 1. 原地(in place)解决问题
     * 2. 时间复杂度O(n), 空间复杂度O(1)
     */
    public void moveZeroes2(int[] nums) {
        int k = 0;                                  // [0...k]的元素均为非0元素

        /** 遍历第i个元素后, 保证[0...i]中所有非0元素都按照顺序排列在[0...k]中 */
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k ++;
            }
        }

        for (int i = k; i < nums.length; i ++) {
            nums[i] = 0;
        }
    }

    /**
     * 1. 原地(in place)解决问题
     * 2. 时间复杂度O(n), 空间复杂度O(1)
     */
    public void moveZeroes3(int[] nums) {
        int k = 0;

        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != 0) {
                swap(nums, k, i);                   // 极端案例: 数组中非0, 所有元素自己和自己进行交换
                k ++;
            }
        }
    }

    public void moveZeroes4(int[] nums) {
        int k = 0;

        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != 0) {
                if (k != i) {
                    swap(nums, k, i);               // k和i对应的位置不同才进行交换
                    k ++;
                } else {
                    k ++;                           // 元素不用自己和自己交换
                }
            }
        }
    }

    /**
     * 个人题解
     */
    public void moveZeroes5(int[] nums) {
        int zeroCount = 0;

        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] == 0) {
                zeroCount ++;
            }
        }

        int end = 1;
        for (int i = 0; i < nums.length - zeroCount; i ++, end ++) {
            while (nums[i] == 0 && nums[end] == 0) {
                end ++;
            }
            if (nums[i] == 0) {
                swap(nums, i, end);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        MoveZeroes m = new MoveZeroes();

        int[] arr = {4, 2, 4, 0, 0, 3, 0, 5, 1, 0};
        int[] arr2 = {0, 1, 0, 3, 12};
        m.moveZeroes1(arr);
        m.moveZeroes1(arr2);

        m.moveZeroes2(arr);
        m.moveZeroes2(arr2);

        m.moveZeroes3(arr);
        m.moveZeroes3(arr2);

        m.moveZeroes4(arr);
        m.moveZeroes4(arr2);

        m.moveZeroes5(arr);
        m.moveZeroes5(arr2);

        for (int i = 0; i < arr.length; i ++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < arr2.length; i ++) {
            System.out.print(arr2[i] + " ");
        }
    }

}
