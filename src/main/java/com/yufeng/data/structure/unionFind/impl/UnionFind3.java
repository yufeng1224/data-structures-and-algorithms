package com.yufeng.data.structure.unionFind.impl;

import com.yufeng.data.structure.unionFind.UF;

/**
 * @description
 *      Union-Find第三版: 基于size的优化的并查集
 * @author yufeng
 * @create 2019-07-30
 */
public class UnionFind3 implements UF {

    private int[] parent;               // 底层维护了一个数组

    private int[] sz;                   // sz[i]表示以i为根的集合中元素个数

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < parent.length; i ++) {
            parent[i] = i;
            sz[i] = 1;                  // 初始化每个元素只有自己一个节点
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }

        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查询
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并
     */
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        /**
         * 1. 根据两个元素所在树的元素个数不同判断合并方向
         * 2. 将元素个数少的集合合并到元素个数多的集合上
         * 3. 目的: 降低树的深度
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
