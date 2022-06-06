package com.yufeng.algorithm.sort;

import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.Arrays;

/**
 * @description
 *      归并排序实现
 * @author yufeng
 * @create 2019-10-15
 */
public class MergeSort {

    private MergeSort(){}

    /** 自顶向下的归并排序 */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) {                                                   // 递归终止条件
            return;
        }

        int mid = l + (r - l) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);

        merge(arr, l, mid, r);                                          // 归并操作
    }

    /** 归并排序算法优化(一) */
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        sort2(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = l + (r - l) / 2;
        sort2(arr, l, mid);
        sort2(arr, mid + 1, r);

        /**
         * 优化点
         *    1. 两个组数互有大小, 才进行归并
         *    2. 如果arr[mid] < arr[mid + 1], 说明两个数组是从小到大的, 不用进行归并了
         */
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);                                      // 进行归并
        }
    }

    /** 合并两个有序的空间 arr[l, mid] 和 arr[mid + 1, r] */
    public static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);

        int i = l, j = mid + 1;

        // 每轮循环为 arr[k] 赋值, 偏移量为l
        for (int k = l; k <= r; k ++) {
            if (i > mid) {                          // 已走出左边有序数组的区间
                arr[k] = temp[j - l];
                j ++;
            } else if (j > r) {                     // 已走出右边有序数组的区间
                arr[k] = temp[i - l];
                i ++;
            } else if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                arr[k] = temp[i - l];               // 左边区间的元素比右边的更小, 将左区间的元素赋值到arr中
                i ++;
            } else {
                arr[k] = temp[j - l];               // 右边区间的元素比左边的更小, 将右区间的元素赋值到arr中
                j ++;
            }
        }
    }

    /** 归并排序算法优化(二) */
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        sort3(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r) {
        /**
         * 递归终止条件优化
         *    1. 当数组区间被切分为只有16个长度时, 当前区间使用插入排序
         *    2. 该方法对于编译型的语言(比如Java、C++)是有好处的, 对其他语言来说不是一个非常稳定的优化
         */
        if (r - l <= 15) {
            InsertionSort.sort5(arr, l, r);
            return;
        }

        int mid = l + (r - l) / 2;
        sort3(arr, l, mid);
        sort3(arr, mid + 1, r);

        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);                                      // 进行归并
        }
    }

    /** 归并排序的内存操作优化: 最终采用此版本! */
    public static <E extends Comparable<E>> void sort4(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);                      // 只创建一个临时数组
        sort4(arr, 0, arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void sort4(E[] arr, int l, int r, E[] temp) {
        if (l >= r) {
            return;
        }

        int mid = l + (r - l) / 2;
        sort4(arr, l, mid, temp);
        sort4(arr, mid + 1, r, temp);

        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge2(arr, l, mid, r, temp);                               // 进行归并
        }
    }

    /** 合并两个有序的空间 arr[l, mid] 和 arr[mid + 1, r] */
    private static <E extends Comparable<E>> void merge2(E[] arr, int l, int mid, int r, E[] temp) {
        System.arraycopy(arr, l, temp, l, r - l + 1);

        int i = l, j = mid + 1;

        /** 使用了公共的数组temp, 且当前数组是没有偏移量的 */
        for (int k = l; k <= r; k ++) {
            if (i > mid) {                          // 已走出左边有序数组的区间
                arr[k] = temp[j];
                j ++;
            } else if (j > r) {                     // 已走出右边有序数组的区间
                arr[k] = temp[i];
                i ++;
            } else if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];                   // 左边区间的元素比右边的更小, 将左区间的元素赋值到arr中
                i ++;
            } else {
                arr[k] = temp[j];                   // 右边区间的元素比左边的更小, 将右区间的元素赋值到arr中
                j ++;
            }
        }
    }

    /** 自底向上的归并排序 */
    public static <E extends Comparable<E>> void sortBU(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;

        // 遍历合并的区间长度
        for (int sz = 1; sz < n; sz += sz) {
            /**
             * 遍历合并的两个区间的起始位置 i
             * 合并 [i, i + sz - 1] 和 [i + sz, Math.min(i + sz + sz - 1, n - 1)]
             */
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge2(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
        }
    }

    /** 自底向上的归并排序: 使用插入排序优化 */
    public static <E extends Comparable> void sortBU2(E[] arr){
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;

        // 使用插入排序优化
        // 遍历一遍，对所有 arr[i, i + 15] 的区间，使用插入排序法
        for(int i = 0; i < n; i += 16) {
            InsertionSort.sort5(arr, i, Math.min(n - 1, i + 15));
        }

        // 遍历合并的区间长度。 sz从16开始
        for (int sz = 16; sz < n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge2(arr, i, i + sz - 1, Math.min(n - 1, i + sz + sz - 1), temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        /** 选择排序、插入排序、归并排序性能比较 */
        int n = 100000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("SelectionSort", arr2);
        SortingHelper.sortTest("InsertionSort", arr3);

        /**
         * 结果对比
         *    MergeSort , n = 100000 : 0.166909 s
         *    SelectionSort , n = 100000 : 20.452264 s
         *    InsertionSort , n = 100000 : 10.013118 s
         */

        /** 归并排序几种实现方式对比 */
        int num = 5000000;
        Integer[] arrM1 = ArrayGenerator.generateRandomArray(num, num);
        Integer[] arrM2 = Arrays.copyOf(arrM1, arrM1.length);
        Integer[] arrM3 = Arrays.copyOf(arrM1, arrM1.length);
        Integer[] arrM4 = Arrays.copyOf(arrM1, arrM1.length);
        Integer[] arrM5 = Arrays.copyOf(arrM1, arrM1.length);
        Integer[] arrM6 = Arrays.copyOf(arrM1, arrM1.length);

        SortingHelper.sortTest("MergeSort1", arrM1);
        SortingHelper.sortTest("MergeSort2", arrM2);
        SortingHelper.sortTest("MergeSort3", arrM3);
        SortingHelper.sortTest("MergeSort4", arrM4);
        SortingHelper.sortTest("MergeSortBU", arrM5);
        SortingHelper.sortTest("MergeSortBU2", arrM6);

        /**
         * 结果对比:
         *    MergeSort1 , n = 5000000 : 3.837925 s
         *    MergeSort2 , n = 5000000 : 1.856977 s
         *    MergeSort3 , n = 5000000 : 1.783245 s
         *    MergeSort4 , n = 5000000 : 1.821354 s
         *    MergeSortBU , n = 5000000 : 2.021177 s
         *    MergeSortBU2 , n = 5000000 : 1.912507 s
         *
         */

    }
}
