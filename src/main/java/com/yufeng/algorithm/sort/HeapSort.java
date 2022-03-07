package com.yufeng.algorithm.sort;

import com.yufeng.data.structure.heap.MaxHeap;
import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.Arrays;

/**
 * @description
 *      堆排序
 * @author yufeng
 * @create 2019-10-21
 */
public class HeapSort {

    private HeapSort() {}

    /**
     * 时间复杂度: O(nlogn); 空间复杂度: O(n)
     */
    public static <E extends Comparable<E>> void sort(E[] data) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for (E e : data) {
            maxHeap.add(e);
        }

        for (int i = data.length - 1; i >= 0; i --) {
            data[i] = maxHeap.extractMax();
        }
    }

    /**
     * 优化堆排序: 使用最大堆原地进行排序
     *    1. 先把整个数组变成最大堆
     *    2. 每一轮将堆中最大元素放到数组末尾
     *    3. 时间复杂度: O(nlogn); 空间复杂度: O(1)
     */
    public static <E extends Comparable<E>> void sort2(E[] data) {
        if (data.length <= 1) {
            return;
        }

        for (int i = (data.length - 2) / 2; i >= 0; i --) {
            siftDown(data, i, data.length);
        }

        for (int j = data.length - 1; j >= 0; j --) {
            swap(data, 0, j);
            siftDown(data, 0, j);
        }
    }

    /**
     * 对data[0, n)形成的最大堆中, 索引为k的元素执行siftDown操作
     */
    private static <E extends Comparable<E>> void siftDown(E[] data, int k, int n) {
        while ( 2 * k + 1 < n) {

            int j = 2 * k + 1;
            if (j + 1 < n && data[j + 1].compareTo(data[j]) > 0) {
                j ++;
            }


            if (data[k].compareTo(data[j]) >= 0) {          // 父节点比它的子节点都大, 那不需要进行下沉操作了
                break;
            }
            swap(data, k, j);                               // 当前节点和子节点交换
            k = j;                                          // 重新赋值, 进行下一轮下沉操作
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 排序性能测试
     */
    private static void sortTest() {
        int n = 1000000;

        /**
         * 堆排序、 归并排序、 快速排序性能比较
         *   1. HeapSort , n = 1000000 : 1.007591 s
         *   2. QuickSortTwoWays , n = 1000000 : 0.408172 s
         *   3. QuickSortThreeWays , n = 1000000 : 0.580265 s
         *   4. MergeSort2 , n = 1000000 : 0.464790 s
         *   5. HeapSort2 , n = 1000000 : 0.593949 s
         */
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("HeapSort", arr);
        SortingHelper.sortTest("QuickSortTwoWays", arr2);
        SortingHelper.sortTest("QuickSortThreeWays", arr3);
        SortingHelper.sortTest("MergeSort2", arr4);
        SortingHelper.sortTest("HeapSort2", arr5);
    }

    public static void main(String[] args) {
        sortTest();
    }
}
