package com.yufeng.data.structure.map;

/**
 * 描述:
 *     自定义映射接口
 * @author yufeng
 * @create 2019-08-10
 */
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();

}
