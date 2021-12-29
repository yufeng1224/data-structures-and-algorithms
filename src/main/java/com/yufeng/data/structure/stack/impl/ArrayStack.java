package com.yufeng.data.structure.stack.impl;

import com.yufeng.data.structure.array.Array;
import com.yufeng.data.structure.stack.Stack;


/**
 * 描述:
 *      1. 底层基于动态数组实现的栈
 *      2. 栈顶对应的是数组尾部, 利用到了数组addLast()操作的均摊时间复杂度为O(1)这一特性
 * @author yufeng
 * @create 2019-07-04
 */
public class ArrayStack<E> implements Stack<E> {

    Array<E> array;

    public ArrayStack() {
        array = new Array<>();
    }

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    /**
     * 返回栈中维护元素的数量
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断栈是否为空
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 返回栈的容量大小
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 入栈
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /**
     * 出栈
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 查看栈顶元素
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i ++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

}
