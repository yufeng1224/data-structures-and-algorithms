package com.yufeng.data.structure.queue;

import com.yufeng.data.structure.queue.impl.*;

import java.util.Random;

/**
 * @description
 *      队列测试类
 * @author yufeng
 * @create 2019-07-06
 */
public class QueueTest {

    public static void main(String[] args) {
        QueueTest queueTest = new QueueTest();

        queueTest.arrayQueueTest();
        queueTest.loopQueueTest();
        queueTest.compareQueue();
        queueTest.dequeTest();

    }

    /**
     * 数组队列测试类
     */
    private void arrayQueueTest() {
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

    /**
     * 循环队列测试类
     */
    private void loopQueueTest() {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 20; i ++) {
            queue.enqueue(i);
        }
        System.out.println(queue);

        LoopQueueWithoutSize<Integer> loopQueueWithoutSize = new LoopQueueWithoutSize<>();
        for (int i = 0; i < 20; i ++) {
            loopQueueWithoutSize.enqueue(i);
        }
        System.out.println(loopQueueWithoutSize);

        LoopQueueWithoutSpaceWaste<Integer> loopQueueWithoutSpaceWaste =
                new LoopQueueWithoutSpaceWaste<>();
        for (int i = 0; i < 20; i ++) {
            loopQueueWithoutSpaceWaste.enqueue(i);
        }
        System.out.println(loopQueueWithoutSpaceWaste);
    }

    /**
     * 双端队列测试类
     */
    public void dequeTest() {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 16; i ++) {
            if (i % 2 == 0) {
                deque.addLast(i);
            } else {
                deque.addFront(i);
            }
        }
        System.out.println(deque.getFront());
        System.out.println(deque.getLast());
        System.out.println(deque);

        System.out.println();

        for (int j = 0; !deque.isEmpty(); j ++) {
            if (j % 2 == 0) {
                deque.removeFront();
            } else {
                deque.removeLast();
            }
        }
    }

    /**
     * 1. 数组队列、循环队列和链式队列的比较
     * 2. 队列性能差距主要体现在出队操作
     * 3. 10W 级别的数据比较:
     *    3-1 ArrayQueue, time: 3.679320382 s
     *    3-2 LoopQueue, time: 0.015162803 s
     *    3-3 LinkedListQueue, time: 0.008856918 s
     * 4. 注释掉出队操作，只执行入队操作:
     *    4-1 ArrayQueue, time: 0.014639904 s
     *    4-2 LoopQueue, time: 0.016635938 s
     *    4-3 LinkedListQueue, time: 0.01242973 s
     */
    private void compareQueue() {
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

    /**
     * 测试使用queue 运行opCount个入队和出队操作所需要的时间, 单位: 秒
     */
    private double testQueue(Queue<Integer> queue, int opCount) {
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

}
