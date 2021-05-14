package com.yufeng.data.structure.queue.test;

import com.yufeng.data.structure.queue.impl.ArrayQueue;

/**
 * 描述:
 *      自定义数组队列测试类
 * @author yufeng
 * @create 2019-07-15
 */
public class ArrayQueueTest {

    public static void main(String[] args) {
        arrayQueueTest01();
    }


    private static void arrayQueueTest01() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i ++) {
            queue.enqueue(i);
        }
        System.out.println(queue.getFront());


        ArrayQueue<Integer> queue1 = new ArrayQueue<>();
        for (int i = 0; i < 10; i ++) {
            queue1.enqueue(i);
            System.out.println(queue1);
            if (i % 3 == 2) {
                queue1.dequeue();
                System.out.println(queue1);
            }
        }
    }
}
