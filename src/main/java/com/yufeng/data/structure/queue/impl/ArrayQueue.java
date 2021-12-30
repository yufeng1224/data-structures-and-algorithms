package com.yufeng.data.structure.queue.impl;

import com.yufeng.data.structure.array.Array;
import com.yufeng.data.structure.queue.Queue;

/**
 * @description
 *      基于数组实现的队列
 * @author yufeng
 * @create 2019-07-06
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 查看队列中元素个数
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断队列是否为空
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 入队: 队尾添加元素
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队: 队首移出元素
     * 时间复杂度: O(n) (超过100万个元素，效率会很低!)
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获得队首数据
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i ++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
