package com.yufeng.leetcode.complexity;

import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.Random;

/**
 * @description
 *      select K 应用
 * @author yufeng
 * @create 2019-10-18
 */
public class FindKthLargest {

    /**
     * 1. 修改定义: 在arr[l, r)中寻找第K小的元素
     * 2. 循环不变量: arr[l, r)
     */
    public static int findKthLargest(Integer[] nums, int k) {
        Random random = new Random();
        return selectK(nums, 0, nums.length, nums.length - k, random);
    }

    // 在arr[l, r)中寻找第K小的元素
    private static int selectK(Integer[] arr, int l, int r, int k, Random random) {
        int p = partition(arr, l, r, random);

        if (k == p) {
            return arr[p];
        }

        if (k < p) {
            return selectK(arr, l, p, k, random);
        }
        return selectK(arr, p + 1, r, k, random);
    }

    private static int findKthLargest2(Integer[] arr, int k) {
        Random random = new Random();

        // 因为循环不变量的变化, r的初始值是arr.length, 不是arr.length - 1
        int l = 0, r = arr.length;

        // 在arr[l, r)范围里寻找第K小的数字, 循环继续条件是l<r, 而不是l<=r
        while (l < r) {
            int p = partition(arr, l, r, random);

            if (k == p) {
                return arr[p];
            }

            // r的修改只变为p而非p-1, 因为我们处理的区间, 右边界是开区间
            if (k < p) {
                r = p;
            } else {
                l = p + 1;
            }
        }
        throw new RuntimeException("No solution!");
    }

    // 在 arr[l, r) 进行 partition
    private static int partition(Integer[] arr, int l, int r, Random random) {
        // 生成[l, r)之间的随机索引
        int p = l + random.nextInt(r - l);
        swap(arr, l, p);

        int i = l + 1, j = r - 1;
        while (true) {
            while (i <= j && arr[i] < arr[l]) {
                i ++;
            }
            while (j >= i && arr[j] > arr[l]) {
                j --;
            }

            if (i >= j) {
                break;
            }
            swap(arr, l, j);
            i ++;
            j --;
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(Integer[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = (int)Math.pow(10, 5);
        Integer[] data = ArrayGenerator.generateOrderArray(n);

        System.out.println(findKthLargest(data, 1001));
        System.out.println(findKthLargest2(data, 1001));
    }
}
