package com.yufeng.data.structure.AVLTree;

import com.yufeng.data.structure.set.Set;

/**
 * 描述:
 *      基于AVL树的set
 * @author yufeng
 * @create 2019-08-29
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AVLTree<E, Object> avl;

    public AVLSet(){
        avl = new AVLTree<>();
    }


    @Override
    public int getSize(){
        return avl.getSize();
    }


    @Override
    public boolean isEmpty(){
        return avl.isEmpty();
    }


    @Override
    public void add(E e){
        avl.add(e, null);
    }


    @Override
    public boolean contains(E e){
        return avl.contains(e);
    }


    @Override
    public void remove(E e){
        avl.remove(e);
    }
}
