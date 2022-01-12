package com.yufeng.algorithm.usingArray;

/**
 * @description
 *      leetCode 75
 * @author yufeng
 * @create 2019-09-03
 */
public class SortColors {

    /**
     * 计算排序
     *    1. 思路: 分别统计0, 1, 2的元素个数
     *    2. 对整个数组遍历了两遍
     *    3. 时间复杂度: O(n); 空间复杂度: O(k), k为元素的取值范围
     */
    public void sortColors1(int[] nums) {
        int[] count = {0, 0, 0};            // 存放0, 1, 2三个元素的频率
        for (int i = 0; i < nums.length; i ++) {
            assert nums[i] >= 0 && nums[i] <= 2;
            count[nums[i]] ++;
        }

        int index = 0;
        for (int i = 0; i < count[0]; i ++) {
            nums[index ++] = 0;
        }
        for (int j = 0; j < count[1]; j ++) {
            nums[index ++] = 1;
        }
        for (int k = 0; k < count[2]; k ++) {
            nums[index ++] = 2;
        }
    }

    /**
     * 三路快速排序
     *    1. 对整个数组只遍历了一遍
     *    2. 时间复杂度: O(n); 空间复杂度: O(1)
     */
    public void sortColors2(int[] nums) {
        int zero = -1;                      // arr[0..zero] == 0
        int two = nums.length;              // arr[two...n-1] == 2

        for (int i = 0; i < two; ) {        // arr[zero+1...i-1] == 1
            if (nums[i] == 1) {
                i ++;
            } else if (nums[i] == 2) {
                two --;
                swap(nums, i, two);
            } else {                        // nums[i] == 0;
                assert nums[i] == 0;
                zero ++;
                swap(nums, zero, i);
                i ++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void printArr(int[] nums) {
        for (int i = 0; i < nums.length; i ++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 1, 1, 0};
        SortColors s = new SortColors();
        s.sortColors1(nums);
        printArr(nums);

        int[] nums2 = {2, 2, 2, 1, 1, 0, 0, 1, 0, 2};
        s.sortColors2(nums2);
        printArr(nums2);
    }
}
