package com.yufeng.data.structure.unionFind.impl;

import com.yufeng.data.structure.unionFind.UF;

/**
 * @description
 *     Union-Find第五版: 路径压缩
 * @author yufeng
 * @create 2019-07-31
 */
public class UnionFind5 implements UF {

    private int[] parent;

    private int[] rank;                 // rank[i]表示以i为根的集合所表示的元素的层数

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i ++) {
            parent[i] = i;
            rank[i] = 1;                // 初始化每个元素只有自己一层
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }

        /** 路径压缩 */
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (rank[qRoot] == rank[pRoot]) {
            parent[pRoot] = qRoot;
            rank[qRoot] ++;                         // rank数组需要进行维护
        } else if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;                  // pRoot树的高度浅, pRoot指向qRoot
        } else {    // (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        }
    }

}
