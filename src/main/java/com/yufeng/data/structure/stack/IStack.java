package com.yufeng.data.structure.stack;

/**
 * 描述:
 *      自定义栈接口
 * @author yufeng
 * @create 2019-07-08
 */
public interface IStack<E> {

    int getSize();

    void push(E e);

    E  pop();

    E  peek();

    boolean isEmpty();

}
