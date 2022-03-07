package com.yufeng.data.structure.heap;

import com.yufeng.data.structure.array.Array;

/**
 * @description
 *      最小堆
 * @author yufeng
 * @create 2019-07-21
 */
public class MinHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MinHeap() {
        data = new Array<>();
    }

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MinHeap(E[] arr) {
        data = new Array<>(arr);
        if (arr.length != 1) {
            for (int i = parent(arr.length - 1); i >= 0; i --) {
                siftDown(i);
            }
        }
    }

    /**
     * 返回堆中的元素个数
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 判断堆中是否为空
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /** 添加三个私有辅助函数 */
    /**
     * 返回当前节点的父节点索引位置
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回当前节点的左子树节点的索引位置
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回当前节点右子树节点的索引位置
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 查看堆中的最小元素
     */
    public E findMin() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not findMin when heap is empty");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最小元素
     */
    public E extractMin() {
        E ret = findMin();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k) {

        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);

            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) < 0) {
                j ++;
            }

            if (data.get(k).compareTo(data.get(j)) <= 0) {
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中的最小元素, 并且替换成元素e
     */
    public E replace(E e) {
        E ret = findMin();

        data.set(0, e);
        siftDown(0);

        return ret;
    }
}
