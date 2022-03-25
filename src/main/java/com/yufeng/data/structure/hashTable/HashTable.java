package com.yufeng.data.structure.hashTable;

import java.util.TreeMap;

/**
 * @description
 *      自定义实现hash表
 * @author yufeng
 * @create 2019-08-08
 */
public class HashTable<K, V> {

    private int capacityIndex = 0;                  // 素数表对应下标
    /** 素数表 */
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917,
            25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};


    private static final int upperTol = 10;         // 哈希冲突容忍度上限
    private static final int lowerTol = 2;          // 哈希冲突容忍度下限

    private TreeMap<K, V>[] hashtable;              // 底层维护的是一个TreeMap数组

    private int size;

    private int M;                                  // 数组初始容量(动态)

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];                 // M个数组空间

        for (int i = 0; i < M; i ++) {
            hashtable[i] = new TreeMap<>();         // 实例化每个TreeMap
        }
    }

    /**
     * 辅助函数
     *   1. 计算key对应的索引位置
     *   2. 将key转换成hashtable对应的索引
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * 返回元素的数量
     */
    public int getSize() {
        return size;
    }

    /**
     * 添加元素
     */
    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);                    // 重新赋值
        } else {
            map.put(key, value);                    // 新增
            size ++;
        }

        // 扩容
        if (size >= upperTol * M && (capacityIndex + 1) < capacity.length) {
            capacityIndex ++;
            resize(capacity[capacityIndex]);
        }
    }

    /**
     * 删除元素
     */
    public V remove(K key) {
        V ret = null;

        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size --;
        }

        if (size < lowerTol * M && (capacityIndex - 1) >= 0) {
            capacityIndex --;
            resize(capacity[capacityIndex]);
        }

        return ret;
    }

    /**
     * 重新赋值
     */
    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        map.put(key, value);
    }

    /**
     * 查询是否存在key
     */
    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    /**
     * 查询key对应的value
     */
    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    /**
     * 扩容
     */
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i ++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i ++) {           // 遍历需要用旧的oldM
            for (K key : hashtable[i].keySet()) {   // 计算新的hash值, 需要使用新的newM
                newHashTable[hash(key)].put(key, hashtable[i].get(key));
            }
        }
        this.hashtable = newHashTable;
    }
}
