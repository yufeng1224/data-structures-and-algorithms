package com.yufeng.data.structure.unionFind.impl;

import com.yufeng.data.structure.unionFind.UF;

/**
 * 描述:
 *      基于size的优化的并查集
 * @author yufeng
 * @create 2019-08-25
 */
public class UnionFind3 implements UF {

    private int[] parent;           // 底层维护了一个数组

    private int[] sz;               // sz[i]表示以i为根的集合中元素个数

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < parent.length; i ++) {
            parent[i] = i;
            sz[i] = 1;              // 初始化每个元素只有自己一个节点
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

        /**
         * 根据两个元素所在树的元素个数不同判断合并方向
         * 将元素个数少的集合合并到元素个数多的集合上
         * 目的: 降低树的深度
         */
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;          // p节点元素个数较少, p的根节点指向q的根节点
            sz[qRoot] += sz[pRoot];
        } else {    // sz[qRoot] <= sz[pRoot]
            parent[qRoot] = pRoot;          // q节点元素个数较少, q的根节点指向p的根节点
            sz[pRoot] += sz[qRoot];
        }
    }

}
