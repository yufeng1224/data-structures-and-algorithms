package com.yufeng.data.structure.heap;

import com.yufeng.data.structure.array.Array;


/**
 * 描述:
 *     底层基于动态数组实现的最大堆的(由于要比较大小, 元素需具有可比较性)
 * @author yufeng
 * @create 2019-08-17
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;              // 底层维护了一个动态数组

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }


    public MaxHeap() {
        data = new Array<>();
    }


    /**
     * heapify 过程
     */
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);

        // i从最后一个非叶子节点开始计算
        for (int i = parent(arr.length - 1);  i >= 0; i --) {
            siftDown(i);
        }
    }


    /**
     * 返回堆中的元素个数
     */
    public int size() {
        return data.getSize();
    }


    /**
     * 判断堆是否为空
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }


    /** 添加三个私有辅助函数 */
    /**
     * 返回当前结点的父结点索引位置
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }


    /**
     * 返回当前结点的左子树节点的索引位置
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }


    /**
     * 返回当前节点右子树结点的索引位置
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }


    /**
     * 向堆中添加元素
     */
    public void add(E e) {
        data.addLast(e);                     // 新增元素添加到数组的尾部
        siftUp(data.getSize() - 1);       // 新添加的元素进行上浮操作
    }


    /**
     * 新添加的堆元素上浮操作
     *    1. 当前结点的值 与 它父亲结点的值进行比较;
     *    2. 如果当前结点值比它父亲节点值大, 当前结点与父亲结点值进行互换;
     *    3. 索引位置k 变更成了它的父结点的索引位置;
     *    4. 再进行下一轮的比较, 直到不满足条件, 则上浮操作完成;
     */
    private void siftUp(int k) {
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k , parent(k));
            k = parent(k);                      // k来到了新的位置!
        }
    }


    /**
     * 查看堆中的最大元素
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        return data.get(0);
    }


    /**
     * 取出堆中的最大元素
     */
    public E extractMax() {
        E ret = findMax();

        data.swap(0, data.getSize() - 1);        // 将最大数的值和最后一位的值交换
        data.removeLast();                           // 删除最后一位, 这样就删除了最大元素
        siftDown(0);                               // 当前0位置元素进行下沉操作

        return ret;                                  // 将删除的最大元素返回
    }


    /**
     * 堆元素下沉操作
     */
    private void siftDown(int k) {

        // 循环终止条件（k已经是叶子节点了), 父节点至少得有一个左叶子结点
        while (leftChild(k) < data.getSize()) {

            int j = leftChild(k);

            // 左叶子结点与右叶子结点进行比较, 获取值较大的那个索引位置
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
                // data[j] 是 leftChild 和 rightChild 中的最大值
            }

            // 父节点比它的子节都打, 那不需要进行下沉操作了。直接跳出循环
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);                            // 当前节点和子节点交换
            k = j;                                      // 重新赋值, 进行下一轮下沉操作
        }
    }


    /**
     * 取出堆中的最大元素, 并且替换成元素e
     */
    public E replace(E e) {

        E ret = findMax();

        // 替换过程
        data.set(0, e);
        siftDown(0);

        return ret;
    }
}



















