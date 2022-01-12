package com.yufeng.algorithm.usingArray;

import com.yufeng.algorithm.util.MyUtil;

/**
 * @description
 *      1. 二分查找法代码演示
 *      2. 有序数列才能使用二分查找法
 *      3. 二分查找法思想在1946年提出, 第一个没有bug的二分查找法在1962年才出现
 * @author yufeng
 * @create 2019-09-03
 */
public class BinarySearch {

    private BinarySearch() {}

    /**
     * 在[l, r]闭区间范围内寻找target
     */
    public int binarySearch(Comparable[] arr, int n, Comparable target) {
        int l = 0, r = n - 1;                   // 在[l...r]的范围内寻找target
        while (l <= r) {                        // 当 l == r时, 区间[l...r]依然是优先的
            int mid = l + (r - l) / 2;          // 注意整型溢出
            if (target.compareTo(arr[mid]) == 0) {
                return mid;
            }
            if (target.compareTo(arr[mid]) > 0) {
                l = mid + 1;                    // target在[mid+1...r]中, [l...mid]一定没有target
            } else {
                r = mid - 1;                    // target在[1...mid-1]中, [mid...r]一定没有target
            }
        }
        return -1;
    }

    /**
     * 在[l, r)右开区间内寻找target
     */
    public int binarySearch2(Comparable[] arr, int n, Comparable target) {
        int l = 0, r = n;                       // 在[l...r)的范围内寻找target
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (target.compareTo(arr[mid]) == 0) {
                return mid;
            }
            if (target.compareTo(arr[mid]) > 0) {
                l = mid + 1;                    // target在[mid+1...r)中, [l...mid]一定没有target
            } else {
                r = mid;                        // target在[1...mid)中, [mid...r)一定没有target
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch b = new BinarySearch();

        int n = (int)Math.pow(10, 7);
        Integer[] data = MyUtil.generateOrderArray(n);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i ++) {
            if (i != b.binarySearch(data, n , i)) {
                throw new IllegalArgumentException("find i failed");
            }
            if (i != b.binarySearch2(data, n , i)) {
                throw new IllegalArgumentException("find i failed");
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Binary Search test complete!");
        System.out.println("Time cost: " + (endTime - startTime) + " ms");
    }
}
