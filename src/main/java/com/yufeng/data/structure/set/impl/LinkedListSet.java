package com.yufeng.data.structure.set.impl;

import com.yufeng.data.structure.linkedList.LinkedList;
import com.yufeng.data.structure.set.Set;

/**
 * @description
 *      基于链表实现的集合
 * @author yufeng
 * @create 2019-07-15
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 时间复杂度: O(n)
     */
    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    /**
     * 时间复杂度: O(n)
     */
    @Override
    public void add(E e) {
        if(!contains(e)) {
            list.addFirst(e);
        }
    }

    /**
     * 时间复杂度: O(n)
     */
    @Override
    public void remove(E e) {
        list.removeElement(e);
    }
}
