package com.yufeng.leetcode.stackAndQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description
 *      Java中优先队列用法演示
 * @author yufeng
 * @create 2019-09-13
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        /** 默认的PriorityQueue, 底层是最小堆 */
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < 10; i ++) {
            int num = (int)(Math.random() * 100);
            pq.add(num);
            System.out.println("insert " + num + " in priority queue.");
        }

        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
        System.out.println();


        /** 使用lambda表达式, 创建底层是最大堆的PriorityQueue */
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(10, (a, b) -> b - a);
        for (int i = 0; i < 10; i ++) {
            int num = (int)(Math.random() * 100);
            pq2.add(num);
            System.out.println("insert " + num + " in priority queue2.");
        }

        while (!pq2.isEmpty()) {
            System.out.print(pq2.poll() + " ");
        }
        System.out.println();
        System.out.println();


        /**
         * 使用自定义的Comparator, 创建个性化的PriorityQueue
         * 注意: 也可以使用lambda表达式, 这里只是为了演示PriorityQueue的不同用法
         */
        class MyCmp implements Comparator<Integer> {

            @Override
            public int compare(Integer a, Integer b) {
                if (a % 10 != b % 10) {
                    return a % 10 - b % 10;
                }
                return a - b;
            }
        }

        PriorityQueue<Integer> pq3 = new PriorityQueue<>(10, new MyCmp());
        for (int i = 0; i < 10; i ++) {
            int num = (int)(Math.random() * 100);
            pq3.add(num);
            System.out.println("insert " + num + " in priority queue3.");
        }

        while (!pq3.isEmpty()) {
            System.out.print(pq3.poll() + " ");
        }
        System.out.println();
        System.out.println();

    }
}
