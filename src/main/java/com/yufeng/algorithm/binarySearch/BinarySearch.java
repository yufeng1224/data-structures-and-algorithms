package com.yufeng.algorithm.binarySearch;

import com.yufeng.leetcode.util.ArrayGenerator;

/**
 * @description
 *      1. 二分查找法代码演示
 *      2. 前提条件: 有序数列才能使用二分查找法
 *      3. 二分查找法思想在1946年提出, 第一个没有bug的二分查找法在1962年才出现
 * @author yufeng
 * @create 2019-09-03
 */
public class BinarySearch {

    private BinarySearch() {}

    /**
     * 递归实现二分查找
     */
    public static <E extends Comparable<E>> int searchR(E[] data, E target) {
        return searchR(data, 0, data.length - 1, target);
    }

    private static <E extends Comparable<E>> int searchR(E[] data, int l, int r, E target) {
        if (l > r) {
            return -1;
        }

        int mid = l + (r - l) / 2;
        if (data[mid].compareTo(target) == 0) {
            return mid;
        }
        if (data[mid].compareTo(target) < 0) {           // target在mid的右边
            return searchR(data, mid + 1, r, target);
        }
        return searchR(data, l, mid - 1, target);     // target在mid的左边

    }

    /**
     * 非递归实现二分查找法
     */
    public static <E extends Comparable<E>> int search(E[] data, E target) {
        int l = 0, r = data.length - 1;

        // 在data[l, r]的范围中查找 target
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (data[mid].compareTo(target) == 0) {
                return mid;
            }

            // 调整区间
            if (data[mid].compareTo(target) < 0) {
                l = mid + 1;                    // target在[mid+1, r]中, [l, mid]一定没有target
            } else {
                r = mid - 1;                    // target在[1, mid-1]中, [mid, r]一定没有target
            }
        }
        return -1;
    }

    /**
     * 更换定义: 在[l, r)右开区间内寻找target
     * 非递归实现二分查找法
     */
    public static <E extends Comparable<E>> int search2(E[] data, E target) {
        int l = 0, r = data.length;

        // 在data[l, r)的范围内寻找 target
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (target.compareTo(data[mid]) == 0) {
                return mid;
            }

            if (target.compareTo(data[mid]) > 0) {
                l = mid + 1;                    // 在 data[mid+1, r) 范围里寻找target
            } else {
                r = mid;                        // 在 data[1, mid)   范围里寻找target
            }
        }
        return -1;
    }

    /**
     * 1. 使用求解 >= target 的最小值索引的思路, 实现 search
     * 2. 在有序数组 data 中判断 target 是否存在, 存在则返回相应索引, 不存在则返回 -1
     */
    public static <E extends Comparable<E>> int search3(E[] data, E target){
        int l = 0, r = data.length;

        // 在 data[l, r] 中寻找解
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (data[mid].compareTo(target) < 0) {
                l = mid + 1;
            } else {                                // data[mid] >= target
                r = mid;
            }
        }

        /**
         * 1. l合法性进行校验
         * 2. 如果 data[l] == target，则返回 l; 否则返回 -1
         */
        if (l < data.length && data[l].compareTo(target) == 0) {
            return l;
        }

        return -1;
    }

    /**
     * 返回 >target 元素中的的最小索引值
     *    1. 如果数组中最小的元素都比target大, 则返回0
     *    2. 如果数组中最大的元素都比target小, 则没有元素符合题意, 返回data.length
     *    3. 因此搜索范围是 [0, data.length]
     */
    public static <E extends Comparable<E>> int upper(E[] data, E target) {
        int l = 0, r = data.length;

        while (l < r) {                        // 在data[l, r]中寻找解
            int mid = l + (r - l) / 2;

            if (data[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else { // arr[mid] > target
                r = mid;
            }
        }
        return l;                               // l==r退出while循环, 所以此处也可return r
    }

    /**
     * 返回 >=target 元素中的最大索引值
     *    1. 如果数组中存在元素等于target, 返回等于target元素中的最大索引值
     *    2. 如果数组中不存在元素等于target, 返回大于target元素中的最小索引值
     */
    public static <E extends Comparable<E>> int ceil(E[] data, E target) {
        int u = upper(data, target);
        if (u - 1 >= 0 && data[u - 1].compareTo(target) == 0) {
            return u - 1;
        }
        return u;
    }

    /**
     * 返回 >=target 的最小值索引
     *    1. 如果数组中存在元素等于target, 返回等于target元素中最小索引
     *    2. 如果数组中不存在元素等于target, 返回大于target元素中的最小索引
     */
    public static <E extends Comparable<E>> int lowerCeil(E[] data, E target) {
        int u = upper(data, target);

        while (u - 1 >= 0) {
            if (data[u - 1].compareTo(target) == 0) {
                u = u -1;
            } else {
                break;
            }
        }
        return u;
    }

    /**
     * 实现方式二: 返回 >=target 的最小值索引
     */
    public static <E extends Comparable<E>> int lowerCeil2(E[] data, E target) {
        int l = 0, r = data.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (target.compareTo(data[mid]) > 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     * 返回 < target 的最大值索引
     *     1. 如果数组中最小的元素都比target大, 则没有元素符合题意, 返回-1
     *     2. 如果数组中所有的元素都比target小, 则返回data.length - 1
     *     3. 因此搜索范围是 [-1, data.length - 1]
     */
    public static <E extends Comparable<E>> int lower(E[] data, E target) {
        int l = -1, r = data.length - 1;

        // 在data[l, r]中寻找解
        while (l < r) {
            int mid = l + (r - l + 1) / 2;              // 上取整, 防止当r=1,l=0时, 搜索空间没有变化, 导致死循环!

            if (data[mid].compareTo(target) < 0) {
                l = mid;
            } else {
                r = mid - 1;
            }

        }
        return l;
    }

    /**
     * 1. 如果数组中不存在元素等于target, 返回小于target元素中的最大索引值
     * 2. 如果数组中存在元素等于target, 返回等于target元素中的最小索引值
     */
    public static <E extends Comparable<E>> int lowerFloor(E[] data, E target){
        int l = lower(data, target);

        /** l + 1 边界检查 */
        if (l + 1 < data.length && data[l + 1].compareTo(target) == 0) {
            return l + 1;
        }
        return l;
    }

    /**
     * 1. < target, 返回最大值索引
     * 2. == target, 返回最大索引
     */
    public static <E extends Comparable<E>> int upperFloor(E[] data, E target){
        int l = -1, r = data.length - 1;

        // 在 data[l, r] 中寻找解
        while (l < r) {
            int mid = l + (r - l + 1) / 2;

            if (data[mid].compareTo(target) <= 0) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int n = (int)Math.pow(10, 4);
        Integer[] data = ArrayGenerator.generateOrderArray(n);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i ++) {
            if (i != search(data, n)) {
                throw new IllegalArgumentException("search find i failed");
            }
            if (i != search2(data, n)) {
                throw new IllegalArgumentException("search2 find i failed");
            }
            if (i != search3(data, n)) {
                throw new IllegalArgumentException("search3 find i failed");
            }
            if (i != searchR(data, n)) {
                throw new IllegalArgumentException("searchR find i failed");
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Binary Search test complete!");
        System.out.println("Time cost: " + (endTime - startTime) + " ms");

        Integer[] arr = {1, 1, 3, 3, 5, 5};

        /** upper 函数测试 */
        for (int i = 0; i <= 6; i ++) {
            System.out.print(upper(arr, i) + " ");          // 0 2 2 4 4 6 6
        }
        System.out.println();

        /** ceil 函数测试 */
        for (int i = 0; i <= 6; i ++) {
            System.out.print(ceil(arr, i) + " ");           // 0 1 2 3 4 5 6
        }
        System.out.println();

        /** lowerCeil 函数测试 */
        for (int i = 0; i <= 6; i ++) {
            System.out.print(lowerCeil(arr, i) + " ");      // 0 0 2 2 4 4 6
        }
        System.out.println();

        /** lowerCeil2 函数测试 */
        for (int i = 0; i <= 6; i ++) {
            System.out.print(lowerCeil2(arr, i) + " ");     // 0 0 2 2 4 4 6
        }
        System.out.println();

        /** lower 函数测试 */
        for (int i = 0; i <= 6; i ++) {
            System.out.print(lower(arr, i) + " ");          // -1 -1 1 1 3 3 5
        }
        System.out.println();

        /** lowerFloor 函数测试 */
        for (int i = 0; i <= 6; i ++) {
            System.out.print(lowerFloor(arr, i) + " ");     // -1 0 1 2 3 4 5
        }
        System.out.println();

        /** upperFloor 函数测试 */
        for( int i = 0; i <= 6; i ++) {
            System.out.print(upperFloor(arr, i) + " ");     // -1 1 1 3 3 5 5
        }
        System.out.println();
    }
}
