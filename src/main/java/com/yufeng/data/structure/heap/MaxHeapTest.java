package com.yufeng.data.structure.heap;

import java.util.Random;

/**
 * @description
 *      自定义堆测试类
 * @author yufeng
 * @create 2019-07-20
 */
public class MaxHeapTest {

    public static void main(String[] args) {

        int n = 1000000;
        Integer[] testData = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < testData.length ; i ++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        /** 将n个元素逐个插入到一个空堆中 */
        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        /** heapify的过程 */
        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }

    /**
     * Without heapify: 1.300572741 s
     * With heapify: 1.209367557 s
     */
    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (int num: testData)
                maxHeap.add(num);
        }

        // 验证堆的正确性
        int[] arr = new int[testData.length];
        for (int i = 0 ; i < testData.length; i ++) {
            arr[i] = maxHeap.extractMax();
        }
        for(int i = 1; i < testData.length; i ++) {
            if (arr[i-1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

}
