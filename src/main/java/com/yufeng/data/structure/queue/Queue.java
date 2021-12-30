package com.yufeng.data.structure.queue;

/**
 * @description
 *      自定义队列接口
 * @author yufeng
 * @create 2019-07-06
 */
public interface Queue<E> {

    void enqueue(E e);

    E  dequeue();

    E  getFront();

    int getSize();

    boolean isEmpty();

}
