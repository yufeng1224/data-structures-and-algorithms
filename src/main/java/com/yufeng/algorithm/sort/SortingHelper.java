package com.yufeng.algorithm.sort;

/**
 * @description
 *      排序工具测试类
 * @author yufeng
 * @create 2019-10-12
 */
public class SortingHelper {

    private SortingHelper(){}

    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i ++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortName, E[] arr) {
        long startTime = System.nanoTime();

        if (sortName.equals("SelectionSort")) {
            SelectionSort.sort1(arr);
        } else if (sortName.equals("InsertionSort")) {
            InsertionSort.sort3(arr);
        }

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;

        if (!SortingHelper.isSorted(arr)) {
            throw new RuntimeException(sortName + " failed");
        } else {
            System.out.println(String.format("%s , n = %d : %f s", sortName, arr.length, time));
        }
    }
}
