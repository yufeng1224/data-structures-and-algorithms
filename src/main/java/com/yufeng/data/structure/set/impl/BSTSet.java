package com.yufeng.data.structure.set.impl;

import com.yufeng.data.structure.binarySearchTree.BST;
import com.yufeng.data.structure.set.Set;

/**
 * 描述:
 *      基于二分搜索树实现的集合
 * @author yufeng
 * @create 2019-08-06
 */
public class BSTSet<E extends Comparable<E>>  implements Set<E> {

    private BST<E> bst;

    public  BSTSet() {
        bst = new BST<>();              // 底层维护了一个二分搜索树
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }


    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }


    /**
     * 自定义实现的二分搜索树中不会有重复元素
     * 时间复杂度: O(logN)
     */
    @Override
    public void add(E e) {
        bst.add(e);
    }


    /**
     * 查看是否包含元素e
     * 时间复杂度: O(logN)
     */
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }


    /**
     * 删除元素
     * 时间复杂度: O(logN)
     */
    @Override
    public void remove(E e) {
        bst.remove(e);
    }
}
