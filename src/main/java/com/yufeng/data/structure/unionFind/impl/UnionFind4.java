package com.yufeng.data.structure.unionFind.impl;

import com.yufeng.data.structure.unionFind.UF;

/**
 * @description
 *     Union-Find第四版: 基于rank的优化
 * @author yufeng
 * @create 2019-07-31
 */
public class UnionFind4 implements UF {

    private int[] parent;

    private int[] rank;                 // rank[i]表示以i为根的集合所表示的数的层数

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i ++) {
            parent[i] = i;
            rank[i] = 1;                // 初始化每个元素只有自己一层
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p) {
        if (p < 0 && p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    /**
     * 查看元素p和元素q是否所属一个集合
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p和元素q所属的集合
     */
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;                         // 元素p和元素q已经属于同一根节点, 无需合并直接返回
        }

        /**
         * 根据两个元素所在树的rank的不同判断合并方向
         * 将rank低的集合合并到rank高的集合上
         * 目的: 降低树的深度
         */
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;          // pRoot树的高度浅, pRoot指向qRoot
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {    // rank[qRoot] = rank[pRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;               // rank数组需要进行维护
        }
    }
}
