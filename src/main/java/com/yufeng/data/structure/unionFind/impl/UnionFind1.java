package com.yufeng.data.structure.unionFind.impl;

import com.yufeng.data.structure.unionFind.UF;

/**
 * @description
 *      Union-Find第一版: 根据数组实现的并查集
 * @author yufeng
 * @create 2019-07-30
 */
public class UnionFind1 implements UF {

    private int[] id;                           // 底层维护了一个数组

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i ++) {
            id[i] = i;                          // 初始的时候每一个元素所属不同的集合
        }
    }

    /**
     * 私有辅助函数: 查找元素p所对应的集合编号
     */
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        return id[p];
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查询操作: 查看元素p和元素q是否所属同一个集合
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);              // 查看集合编号是否相等
    }

    /**
     * 合并操作: 合并元素p和元素q所在的集合
     */
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {
            return;                             // 元素p和元素q已经属于同一集合, 无需合并直接返回
        }

        for (int i = 0; i < id.length; i ++) {
            if (id[i] == pID) {
                id[i] = qID;                    // 查询遍历, 将集合中属于p的元素全部合并到集合q中
            }
//            if (id[i] == qID) {
//                id[i] = pID;
//            }
        }
    }
}
