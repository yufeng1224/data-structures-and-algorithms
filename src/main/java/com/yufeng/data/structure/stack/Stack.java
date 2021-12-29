package com.yufeng.data.structure.stack;

/**
 * @description
 *      自定义栈接口
 * @author yufeng
 * @create 2019-07-04
 */
public interface Stack<E> {

    int getSize();

    void push(E e);

    E pop();

    E peek();

    boolean isEmpty();

}
