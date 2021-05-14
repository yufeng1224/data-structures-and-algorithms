package com.yufeng.data.structure.queue.test;

import com.yufeng.data.structure.queue.Queue;
import com.yufeng.data.structure.queue.impl.ArrayQueue;
import com.yufeng.data.structure.queue.impl.LinkedListQueue;
import com.yufeng.data.structure.queue.impl.LoopQueue;

import java.util.Random;

/**
 * 描述:
 *      数组队列、循环队列和链式队列的比较
 * @author yufeng
 * @create 2019-07-31
 */
public class CompareQueue {

    // 测试使用queue 运行 opCount 个 enqueue 和 dequeue 操作所需要的时间, 单位: 秒
    private static double testQueue(Queue<Integer> queue, int opCount) {

        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i ++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i ++) {
            queue.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }


    /**
     * 性能比较
     *    1. 数组队列和循环队列性能差距主要在出队操作;
     *
     *
     * 10W 级别的数据比较!
     *    1. ArrayQueue, time: 3.679320382 s
     *    2. LoopQueue, time: 0.015162803 s
     *    3. LinkedListQueue, time: 0.008856918 s
     *
     *
     * 注释掉出队操作，只执行入队操作。
     *    1. ArrayQueue,      time: 0.014639904 s
     *    2. LoopQueue,       time: 0.016635938 s
     *    3. LinkedListQueue, time: 0.01242973 s
     *
     */
    public static void main(String[] args) {

        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");

        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>();
        double time3 = testQueue(listQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + " s");
    }
}


















