package com.yufeng.data.structure.queue.impl;

import com.yufeng.data.structure.queue.Queue;

/**
 * @description
 *      实现循环队列, 并且没有浪费1个空间
 * @author yufeng
 * @create 2019-07-07
 */
public class LoopQueueWithoutSpaceWaste<E> implements Queue<E> {

    private E[] data;

    private int front, tail;

    private int size;

    public LoopQueueWithoutSpaceWaste(int capacity) {
        // 由于不浪费空间, 所以data静态数组的大小是capacity, 而不是capacity + 1
        data = (E[])new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueueWithoutSpaceWaste() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public void enqueue(E e) {
        // 不再使用LoopQueue中front和tail之间的关系来判断队列是否已满, 而是直接使用size
        if (size == getCapacity()) {
            resize (getCapacity() << 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret = data[front];
        data[front] = null;                         // help GC
        front = (front + 1) % data.length;
        size --;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize (getCapacity() >> 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        // 不再使用LoopQueue中front和tail之间的关系来判断队列是否为空
        return size == 0;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
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
        res.append(String.format("Queue size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = 0 ; i < size; i ++) {
            res.append(data[(front + i) % data.length]);
            if ((i + front + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
