package com.yufeng.data.structure.unionFind.impl;

import com.yufeng.data.structure.unionFind.UF;

/**
 * 描述:
 *      第二版的Union-Find(Quick Union设计思路)
 * @author yufeng
 * @create 2019-08-24
 */
public class UnionFind2 implements UF {

    private int[] parent;           // 底层维护了一个数组

    public UnionFind2(int size) {
        parent = new int[size];
        for (int i = 0; i < parent.length; i ++) {
            parent[i] = i;
        }
    }


    @Override
    public int getSize() {
        return parent.length;
    }


    /**
     * 查找过程, 查找元素p所对应的集合编号
     * 时间复杂度: O(h), h为树的高度
     */
    private int find(int p) {
        if (p < 0 && p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]) {        // 当p没有指向自己, 说明不是根节点
            p = parent[p];              // 寻找它的父亲节点
        }
        return p;                       // 返回根节点
    }


    /**
     * 查看元素p和元素q是否所属一个集合(和第一版类似)
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }


    /**
     * 合并元素p 和 元素q所属的集合
     * 时间复杂度: O(h), h为树的高度
     */
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;                     // 元素p和元素q已经属于同一根节点, 无需合并直接返回
        }
        parent[pRoot] = qRoot;          // p的根节点指向q的根节点
    }

}
