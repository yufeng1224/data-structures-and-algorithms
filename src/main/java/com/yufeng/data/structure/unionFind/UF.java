package com.yufeng.data.structure.unionFind;

/**
 * @description
 *    并查集接口(不考虑向并查集中添加或删除一个元素)
 * @author yufeng
 * @create 2019-07-30
 */
public interface UF {

    /**
     * 返回并查集中的元素个数
     */
    int getSize();

    /**
     * 查询操作(Find): 两个元素是否所属一个集合(是否可以连接)
     */
    boolean isConnected(int p, int q);

    /**
     * 合并操作(Union): 将两个元素并在一起
     */
    void unionElements(int p , int q);

}
