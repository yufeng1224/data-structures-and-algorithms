package com.yufeng.data.structure.queue.impl;

import com.yufeng.data.structure.queue.Queue;

/**
 * @description
 *      实现循环队列, 并且不使用size变量, 只使用front和tail变量
 * @author yufeng
 * @create 2019-07-07
 */
public class LoopQueueWithoutSize<E> implements Queue<E> {

    private E[] data;

    private int front, tail;

    public LoopQueueWithoutSize(int capacity) {
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
    }

    public LoopQueueWithoutSize() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        /**
         * 1. 如果tail >= front, 则队列中的个数就是tail - front
         * 2. 如果tail < front, 说明队列"循环"起来了, 此时队列中的元素个数为 tail - front + data.length
         *    (此时，data中没有元素的数目为front - tail, 整体元素个数就是
         *     data.length - (front - tail) = data.length + tail - front)
         */
        return tail >= front ? tail - front : tail - front + data.length;
    }

    @Override
    public void enqueue(E e) {
        // 队列是否已满
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue!");
        }

        E ret = data[front];
        data[front] = null;                         // help GC
        front = (front + 1) % data.length;

        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize (getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty!");
        }
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity + 1];
        int size = getSize();
        for (int i = 0; i < size; i ++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    /**
     * 打印循环队列
     */
    @Override
    public String toString() {
        StringBuilder res  = new StringBuilder();
        res.append(String.format("Queue size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        for (int i = front ; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
