package com.yufeng.data.structure.queue.impl;

import com.yufeng.data.structure.queue.Queue;

/**
 * 描述:
 *     自定义循环队列
 *        1. front: 指向队首下标;
 *        2. tail:  指向队尾下标;
 *        3. front == tail,  队列为空;
 *        4. (tail + 1) % getCapacity() == front, 队列满了;
 *        5. 重点: tail 下标为空，该位置上是没有元素的;
 *        6. 循环队列会有一个空间上的浪费;
 * @author yufeng
 * @create 2019-07-16
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    // 指向队首
    private int front;

    // 指向队尾
    private int tail;

    private int size;   // jdk中的size = (tail - front) > 0 ? tail - front : (tail - front) + getCapacity()

    public LoopQueue() {
        this(10);
    }


    /**
     * 构造函数，有意识浪费一个空间
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
     * 查看队列首部元素
     */
    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty.");
        }
        return data[front];
    }


    /**
     * 入队(操作的是队尾)
     *    1. 入队操作需要维护队尾下标tail和size
     *    2. tail指针的所在的位置没有元素。比如有10个元素, a[0]~a[9]都满了, tail 指向的是10。
     *       (数组队列的tail和链表的tail有区别, 链表的tail上面有元素)
     * 均摊时间复杂度: O(1)
     */
    @Override
    public void enqueue(E e) {
        // 队列是否已满
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);      // 进行扩容操作
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }


    /**
     * 出队(操作的是队首)
     *    1. 出队操作需要维护队首下标front和size
     * 均摊时间复杂度: O(1)
     */
    @Override
    public E dequeue() {
        // 队列是否为空
        if(isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from empty queue");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);      // 进行缩容操作
        }
        return ret;
    }


    /**
     * 扩容操作(将原先的队列按顺序进行重排)
     */
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity + 1];
        for (int i = 0; i < newData.length; i ++) {                     // 第一种循环遍历方式
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;                                                 // 重置队首和队尾
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
        for (int i = front ; i != tail; i = (i+1) % data.length) {          // 第二种遍历循环方式
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return super.toString();
    }
}
