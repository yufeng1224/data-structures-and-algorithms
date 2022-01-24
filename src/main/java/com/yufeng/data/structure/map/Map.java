package com.yufeng.data.structure.map;

/**
 * @description
 *     自定义映射接口
 * @author yufeng
 * @create 2019-07-17
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
