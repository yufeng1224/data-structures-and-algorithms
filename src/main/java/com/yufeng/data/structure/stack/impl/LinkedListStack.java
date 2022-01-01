package com.yufeng.data.structure.stack.impl;


import com.yufeng.data.structure.linkedList.LinkedList;
import com.yufeng.data.structure.stack.Stack;

/**
 * @description
 *      1. 底层基于单向链表实现的栈
 *      2. 只操作链表的头结点, 利用了单向链表addFirst()的操作, 时间复杂度为O(1)这一特性;
 * @author yufeng
 * @create 2019-07-09
 */
public class LinkedListStack<E> implements Stack<E> {

    // 内部维护了一个单向链表
    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    /**
     * 返回栈中维护元素的数量
     */
    @Override
    public int getSize() {
        return list.getSize();
    }

    /**
     * 判断栈是否为空
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 入栈
     */
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    /**
     * 出栈
     */
    @Override
    public E pop() {
        return list.removeFirst();
    }

    /**
     * 查看栈顶元素
     */
    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
}
