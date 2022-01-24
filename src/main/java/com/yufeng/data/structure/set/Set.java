package com.yufeng.data.structure.set;

/**
 * @description
 *      集合接口
 * @author yufeng
 * @create 2019-07-15
 */
public interface Set<E> {

    /**
     * 向集合中添加元素(根据集合的特性, 不能添加重复元素)
     */
    void add(E e);


    /**
     * 删除集合中的元素e
     */
    void remove(E e);


    /**
     * 判断集合是否有元素e
     */
    boolean contains(E e);


    /**
     * 获取集合中元素的个数
     */
    int getSize();


    /**
     * 判断集合是否为空(空集)
     */
    boolean isEmpty();

}
