package com.yufeng.data.structure.heap.test;

import com.yufeng.data.structure.heap.PriorityQueue;

/**
 * 描述:
 *     优先队列测试类
 * @author yufeng
 * @create 2019-08-19
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        test01();
    }


    private static void test01() {
        PriorityQueue pq = new PriorityQueue();
        for(int i = 20; i >= 0; i --) {
            pq.enqueue(i);
        }
        System.out.println(pq.toString());
    }
}
