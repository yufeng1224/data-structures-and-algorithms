package com.yufeng.data.structure.queue.impl;

import com.yufeng.data.structure.queue.Queue;

/**
 * @description
 *     1. 基于数组实现循环队列
 *     2. 规则定义
 *        2-1 front: 指向队首; tail: 指向队尾
 *        2-2 front == tail, 队列为空
 *        2-3 (tail + 1) % data.length == front, 队列满了
 *        2-4 tail下标为空, 该位置上是没有元素的。因此循环队列会有一个空间上的浪费
 * @author yufeng
 * @create 2019-07-06
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    private int front, tail;

    private int size;

    public LoopQueue() {
        this(10);
    }

    /**
     * 构造函数, 有意识浪费一个空间
     */
    public LoopQueue(int capacity) {
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    /**
     * 获取队列的容量
     */
    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 判断队列是否为空
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 获取队列的元素数量
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 查看队首元素
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty.");
        }
        return data[front];
    }

    /**
     * 入队: 需维护队尾下标tail和元素数量size
     */
    @Override
    public void enqueue(E e) {
        // 队列是否已满
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    /**
     * 出队: 需维护队首下标front和size
     */
    @Override
    public E dequeue() {
        // 队列是否为空
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from empty queue");
        }

        E ret = data[front];
        data[front] = null;                             // help GC
        front = (front + 1) % data.length;
        size --;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);       // 缩容
        }
        return ret;
    }

    /**
     * 调整容量并将原先的队列按顺序进行重排
     */
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity + 1];
        for (int i = 0; i < newData.length; i ++) {                     // 第一种循环遍历方式
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;                                                 // 重置队首和队尾下标
        front = 0;
        tail = size;
    }

    /**
     * 打印循环队列
     */
    @Override
    public String toString() {
        StringBuilder res  = new StringBuilder();
        res.append(String.format("Queue size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front ; i != tail; i = (i + 1) % data.length) {          // 第二种遍历循环方式
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
