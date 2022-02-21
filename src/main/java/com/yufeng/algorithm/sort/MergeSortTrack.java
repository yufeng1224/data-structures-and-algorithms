package com.yufeng.algorithm.sort;

/**
 * @description
 *      在程序上调试跟踪归并排序法
 * @author yufeng
 * @create 2019-10-16
 */
public class MergeSortTrack {

    private MergeSortTrack(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        sort(arr, 0, arr.length - 1, 0);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, int depth){
        // 生成深度字符串
        String depthString = generateDepthString(depth);

        // 打印当前 sort 处理的数组区间信息
        System.out.print(depthString);
        System.out.println(String.format("mergeSort arr[%d, %d]", l, r));

        if (l >= r)
            return;

        int mid = l + (r - l) / 2;
        sort(arr, l, mid, depth + 1);
        sort(arr, mid + 1, r, depth + 1);

        // 打印这次 merge 要处理的区间范围
        System.out.print(depthString);
        System.out.println(String.format("merge arr[%d, %d] and arr[%d, %d]", l, mid, mid + 1, r));

        MergeSort.merge(arr, l, mid, r);

        // 打印 merge 后的数组
        System.out.print(depthString);
        System.out.print(String.format("after mergeSort arr[%d, %d] :", l, r));
        for(E e: arr)
            System.out.print(e + " ");
        System.out.println();
    }

    private static String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < depth ; i ++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args){
        Integer[] arr = {7, 1, 4, 2, 8, 3, 6, 5};
        MergeSortTrack.sort(arr);
    }
}
