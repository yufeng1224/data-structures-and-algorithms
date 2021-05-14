package com.yufeng.data.structure.segment;

/**
 * 描述:
 *     线段树实现
 * @author yufeng
 * @create 2019-08-20
 */
public class SegmentTree<E> {

    private E[] data;           // 底层使用数组维护数据的副本

    private E[] tree;           // 底层使用数组来表示线段树

    private Merger<E> merger;


    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i ++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];                 // 开辟元素数量的4倍静态空间, 用来存储结点(用空间换时间)
        buildSegmentTree(0,0, data.length -1);      // 构建线段树
    }


    /** 两个私有辅助函数 (与最大堆中的辅助函数相同) */
    /**
     * 在完全二叉树的数组表示中
     * 返回当前结点的左子树结点的索引位置
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }


    /**
     * 在完全二叉树的数组表示中
     * 返回当前结点的右子树结点的索引位置
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }


    /**
     * 构建线段树: 在treeIndex的位置创建表示区间[l...r]的线段树
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归终止的情况
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // int mid = (l + r) / 2;    这种写法, 当l和r都特别大的时候, l+r 可能会出现整型溢出的情况, 所以做如下改进!
        int mid = l + (r - l) / 2;

        // 递归调用过程
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 融合情况根据业务自定义
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    /**
     * 获取线段树中的元素数量
     */
    public int getSize() {
        return data.length;
    }


    /**
     * 获取指定位置的某个数
     */
    public E get(int index) {
        if (index < 0 || index > data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }


    /**
     * 查询操作 ———— 返回区间 [queryL, queryR]的值
     * 时间复杂度: O(logN)
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return query(0, 0 , data.length - 1, queryL, queryR);
    }


    /**
     * 在以 treeID 为根的线段树中[l...r]的范围里, 搜索区间 [queryL ... queryR]的值
     * 线段树的查询操作, 时间复杂度是 O(logN)
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 递归终止条件
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);                               // 左边区间与右边区间结果 融合操作
    }


    /**
     * 将index 位置的值, 更新为e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }

        data[index] = e;
        set(0, 0, data.length - 1 , index , e);
    }


    /**
     * 在以treeIndex为根的线段树中更新index的值为e
     * 时间复杂度 ———— O(logN)
     */
    public void set(int treeIndex, int l, int r, int index, E e) {

        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            set(leftTreeIndex, l, mid, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');

        for (int i = 0 ; i < tree.length ; i ++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
