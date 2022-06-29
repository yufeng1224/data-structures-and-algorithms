package com.yufeng.algorithm.sort;

import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.Arrays;

/**
 * @description
 *     冒泡排序
 * @author yufeng
 * @create 2019-10-23
 */
public class BubbleSort {

    private BubbleSort() {}

    public static <E extends Comparable<E>> void sort(E[] data) {

        for (int i = 0; i + 1 < data.length; i ++) {            // n - 1 轮循环
            /** arr[n-i, n) 已排好序, 通过冒泡在 arr[n-i-1] 位置上放合适的元素 */
            for (int j = 0; j < data.length - i - 1; j ++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    ArrayGenerator.swap(data, j, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序优化(一)
     *    1. 如果未排序的所有元素在此轮比较中都没有交换位置, 说明这些元素都已按从小到大排好序
     *    2. 包括前面轮次中已排好序的元素, 说明整个数组已经排序好了, 可以直接跳出
     *    3. 插入排序算法也有类似性质
     */
    public static <E extends Comparable<E>> void sort2(E[] data) {

        for (int i = 0; i + 1 < data.length; i ++) {            // n - 1 轮循环
            boolean isSwapped = false;

            for (int j = 0; j < data.length - i - 1; j ++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    ArrayGenerator.swap(data, j, j + 1);
                    isSwapped = true;
                }
            }

            /** 此轮元素没有交换位置, 说明已经排好序 */
            if (!isSwapped) {
                break;
            }
        }
    }

    /**
     * 冒泡排序优化(二)
     *    1. 如果未排序的所有元素在此轮比较中, j+1后面的元素都没有交换位置, 说明j+1索引后面位置的元素都已按从小到大排好序
     *    2. 在下一轮比较中, 只需要从data[0]遍历到data[j]进行比较即可
     */
    public static <E extends Comparable<E>> void sort3(E[] data) {

        for (int i = 0; i + 1 < data.length; ) {            // n - 1 轮循环
            int lastSwappedIndex = 0;

            for (int j = 0; j < data.length - i - 1; j ++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    ArrayGenerator.swap(data, j, j + 1);
                    lastSwappedIndex = j + 1;
                }
            }

//            if (lastSwappedIndex == 0) break;
            i = data.length - lastSwappedIndex;
        }
    }

    /** 冒泡排序 */
    public static <E extends Comparable<E>> void sort4(E[] data) {

        for (int i = 0; i + 1 < data.length; i ++) {
            /**  arr[0, i) 已排好序, 通过冒泡在 arr[i] 位置放上合适的元素。从后往前进行比较 */
            for (int j = data.length - 1; j > i; j --) {
                if (data[j - 1].compareTo(data[j]) > 0) {
                    ArrayGenerator.swap(data, j - 1, j);
                }
            }
        }
    }

    public static <E extends Comparable<E>> void sort5(E[] data) {
        for (int i = 0; i + 1 < data.length; i ++){

            /** arr[0, i) 已排好序, 通过冒泡在 arr[i] 位置放上合适的元素 */
            boolean isSwapped = false;
            for (int j = data.length - 1; j > i; j --)
                if (data[j - 1].compareTo(data[j]) > 0) {
                    ArrayGenerator.swap(data, j - 1, j);
                    isSwapped = true;
                }

            if (!isSwapped) {
                break;
            }
        }
    }

    public static <E extends Comparable<E>> void sort6(E[] data){

        for (int i = 0; i + 1 < data.length; ) {
            int lastSwappedIndex = data.length - 1;

            for (int j = data.length - 1; j > i; j --) {
                if (data[j - 1].compareTo(data[j]) > 0) {
                    ArrayGenerator.swap(data, j - 1, j);
                    lastSwappedIndex = j - 1;
                }
            }
            i = lastSwappedIndex + 1;
        }
    }

    public static void main(String[] args) {
        int n = 50000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        System.out.println("Random Array Test");
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);        // 反而慢, 因为多了swap指令, 开销增加了
        SortingHelper.sortTest("BubbleSort3", arr3);

        System.out.println();

        Integer[] arr4 = ArrayGenerator.generateOrderArray(n);
        Integer[] arr5 = Arrays.copyOf(arr4, arr4.length);
        Integer[] arr6 = Arrays.copyOf(arr4, arr4.length);
        System.out.println("Order Array Test");
        SortingHelper.sortTest("BubbleSort", arr4);
        SortingHelper.sortTest("BubbleSort2", arr5);
        SortingHelper.sortTest("BubbleSort3", arr6);
    }
}



















