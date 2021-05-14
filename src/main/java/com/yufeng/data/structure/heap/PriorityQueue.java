package com.yufeng.data.structure.heap;

import com.yufeng.data.structure.queue.Queue;

/**
 * 描述:
 *      基于最大堆实现的优先队列
 * @author yufeng
 * @create 2019-08-18
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;         // 底层维护的是一个最大堆

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }


    @Override
    public int getSize() {
        return maxHeap.size();
    }


    @Override
    public boolean isEmpty() {
        return  maxHeap.isEmpty();
    }


    /**
     * 最大堆所对应的堆顶元素
     */
    @Override
    public E getFront() {
        return maxHeap.findMax();
    }


    /**
     * 将元素e进行入队
     */
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }


    /**
     * 出队操作(取出堆顶元素)
     */
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }
}
















