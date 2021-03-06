package com.yufeng.algorithm.sort;

import com.yufeng.leetcode.util.ArrayGenerator;

import java.util.Arrays;
import java.util.Random;

/**
 * @description
 *      快速排序算法实现
 * @author yufeng
 * @create 2019-10-18
 */
public class QuickSort {

    private QuickSort(){}

    public static<E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序实现一
     *    1. 对于完全有序的数组, 当前算法有明显缺陷
     *    2. 时间复杂度会变成O(n^2), 递归深度为O(n)并出现栈溢出的错误
     *    3. 假设数据量是100W, 有序的数组, 快速排序的递归深度变为100W, 而归并排序的递归深度也只有20
     *    4. 缺陷造成原因: 标定点划分后的数据区间不平均导致
     */
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    public static<E extends Comparable<E>> void sort2(E[] arr) {
        sort2(arr, 0, arr.length - 1);
    }

    /**
     * 使用插入排序优化快速排序
     */
    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r) {
        if (r - l <= 15) {
            InsertionSort.sort5(arr, l , r);
            return;
        }

        int p = partition(arr, l, r);
        sort2(arr, l, p - 1);
        sort2(arr, p + 1, r);
    }

    /**
     * partition分组: arr[l+1, j] < v; arr[j+1, i] >= v
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        int j = l;
        for (int i = l + 1; i <= r; i ++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j ++;
                ArrayGenerator.swap(arr, i, j);
            }
        }
        ArrayGenerator.swap(arr, l, j);                 // 最后将标定点放在正确的索引位置
        return j;
    }

    public static<E extends Comparable<E>> void sort3(E[] arr) {
        Random random = new Random();
        sort3(arr, 0, arr.length - 1, random);
    }

    /**
     * 实现一进阶
     *    1. 为快速排序添加随机化
     *    2. 还存在的缺陷: 假设数组中100W个元素都是相同的, 还是会退化成链表导致栈溢出错误
     */
    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r, Random random) {
        if (l >= r) {
            return;
        }

        int p = partition2(arr, l, r, random);
        sort3(arr, l, p - 1, random);
        sort3(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random random) {
        /** 生成[l, r]之间的随机索引, 然后与arr[l]交换 */
        int p = random.nextInt(r - l + 1) + l;
        ArrayGenerator.swap(arr, l, p);

        return partition(arr, l, r);
    }

    /**
     * 双路快速排序
     */
    public static<E extends Comparable<E>> void sortTwoWays(E[] arr) {
        Random random = new Random();
        sortTwoWays(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void sortTwoWays(E[] arr, int l, int r, Random random) {
        if (l >= r) {
            return;
        }

        int p = partitionTwoWays(arr, l, r, random);
        sortTwoWays(arr, l, p - 1, random);
        sortTwoWays(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partitionTwoWays(E[] arr, int l, int r, Random random) {
        int p = random.nextInt(r - l + 1) + l;
        ArrayGenerator.swap(arr, l, p);

        // 循环不变量 arr[l+1...i-1] <= v; arr[j+1...r] >= v
        int i = l + 1, j = r;
        while (true) {                                          // 虽然有两重循环, 但是时间复杂度是O(n)
            while (i <= j && arr[i].compareTo(arr[l]) < 0) {
                i ++;
            }

            while (j >= i && arr[j].compareTo(arr[l]) > 0) {
                j --;
            }

            /**
             * 上面循环结束, 存在2种可能:
             *    1. i,j指向的元素不符合条件
             *    2. 所有元素都遍历完了
             */
            if (i >= j) {                       // i和j指向同一个元素, i=j说明该元素等于标定点
                break;
            }

            ArrayGenerator.swap(arr, i, j);
            i ++;
            j --;
        }

        ArrayGenerator.swap(arr, l, j);
        return j;
    }

    /**
     * 三路快速排序
     */
    public static<E extends Comparable<E>> void sortThreeWays(E[] arr) {
        Random random = new Random();
        sortThreeWays(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void sortThreeWays(E[] arr, int l, int r, Random random) {
        if (l >= r) {
            return;
        }
        // 生成[l, r]之间的随机索引
        int p = random.nextInt(r - l + 1) + l;
        ArrayGenerator.swap(arr, l, p);

        /** arr[l+1, lt] < v, arr[lt+1, i-1] == v, arr[gt, r] > v */
        int lt = l, i = l + 1, gt = r + 1;

        while (i < gt) {
            if (arr[i].compareTo(arr[l]) < 0) {         // 扩充小于v的空间
                lt ++;
                ArrayGenerator.swap(arr, i, lt);
                i ++;
            } else if (arr[i].compareTo(arr[l]) > 0) {  // 扩充大于v的空间。 注意: 交换元素后, i不能++, 因为是新元素!
                gt --;
                ArrayGenerator.swap(arr, i, gt);
            } else {                                    // arr[i] == arr[l], 相当于扩充等于v的空间
                i ++;
            }
        }
        ArrayGenerator.swap(arr, l, lt);
        /** 最终的数组空间变成: arr[l, lt-1] < v, arr[lt, gt-1] == v, arr[gt, r] > v */

        sortThreeWays(arr, l, lt - 1, random);
        sortThreeWays(arr, gt, r, random);
    }

    /**
     * 递归实现select K 问题
     *    1. 在arr[l, r]的范围里求解整个数组的第K小元素并返回
     *    2. K是索引, 从0开始计算
     */
    public static<E extends Comparable<E>> E selectKByRecursion(E[] arr, int k) {
        Random random = new Random();
        return selectKByRecursion(arr, 0, arr.length - 1, k, random);
    }

    private static<E extends Comparable<E>> E selectKByRecursion(E[] arr, int l, int r, int k, Random random) {
        int p = partition2(arr, l, r, random);

        if (k == p) {
            return arr[p];
        }
        if (k < p) {
            return selectKByRecursion(arr, l, p - 1, k, random);
        }
        return selectKByRecursion(arr, p + 1, r, k, random);
    }

    /**
     * 非递归实现select K 问题
     */
    public static <E extends Comparable<E>> E selectK(E[] arr, int k) {
        Random random = new Random();

        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int p = partition2(arr, l, r, random);

            if (k == p) {
                return arr[p];
            }
            if (k < p) {
                r = p - 1;
            } else {
                l = p + 1;
            }
        }
        throw new RuntimeException("Index k is Illegal. k = " + k);
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);

        /** 归并排序与快速排序比较 */
        Integer[] arr1 = Arrays.copyOf(arr, arr.length);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("MergeSort", arr1);
        SortingHelper.sortTest("QuickSort", arr2);

        /** 快速排序sort和使用插入排序优化的快速排序对比 */
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("QuickSort", arr3);          // 0.350156 s
        SortingHelper.sortTest("QuickSort2", arr4);         // 0.288409 s

        /** 完全有序的数组快速排序sort和sort3函数对比 */
        int n2 = 50000;
        Integer[] arr5 = ArrayGenerator.generateOrderArray(n2);
        Integer[] arr6 = Arrays.copyOf(arr5, arr5.length);

        SortingHelper.sortTest("QuickSort3", arr6);         // 0.025466 s
        SortingHelper.sortTest("QuickSort", arr5);          // StackOverflowError

        /** 三路快排、双路快排和单路快排比较 */
        Integer[] arr7 = Arrays.copyOf(arr, arr.length);
        Integer[] arr8 = Arrays.copyOf(arr, arr.length);
        Integer[] arr9 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("QuickSort", arr7);              // 0.447744 s
        SortingHelper.sortTest("QuickSortTwoWays", arr8);       // 0.571820 s
        SortingHelper.sortTest("QuickSortThreeWays", arr9);     // 0.633751 s

        /** select k 问题测试 */
        System.out.println(selectKByRecursion(arr1, 1000));
        System.out.println(selectK(arr1, 1000));
    }
}
