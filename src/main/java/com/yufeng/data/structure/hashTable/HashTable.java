package com.yufeng.data.structure.hashTable;

import java.util.TreeMap;

/**
 * 描述:
 *      自定义实现hash表
 * @author yufeng
 * @create 2019-09-11
 */
public class HashTable<K, V> {

    // 素数表
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917,
            25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10;         // 哈希冲突容忍度上限

    private static final int lowerTol = 2;          // 哈希冲突容忍度下限

    private int capacityIndex = 0;                  // 哈希表初始容量对应 capacity[0]

    // private static final int initCapacity = 7;   // 初始哈希表容量

    private TreeMap<K, V>[] hashtable;              // 底层维护的是一个TreeMap数组

    private int size;                               // 元素数量

    private int M;                                  // 数组初始容量(动态)

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];                 // M个数组空间

        for (int i = 0; i < M; i ++) {
            hashtable[i] = new TreeMap<>();         // 实例化每个TreeMap
        }
    }


    /** 辅助函数 */
    /**
     * 将key转换成hashtable对应的索引
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }


    public int getSize() {
        return size;
    }


    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size ++;
        }

        // 扩容操作
        if (size >= upperTol * M && (capacityIndex + 1) < capacity.length) {
            capacityIndex ++;
            resize(capacity[capacityIndex]);
        }

//        if (size >= upperTol * M) {
//            resize(2 * M);                  // 进行扩容操作。(缺陷: 2*M 不是素数)
//        }
    }


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

//        if (size < lowerTol * M && M / 2 >= initCapacity) {
//            resize(M / 2);                  // 进行缩容操作
//        }
    }


    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        map.put(key, value);
    }


    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }


    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }


    /** 私有辅助函数 */
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i ++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i ++) {
            // TreeMap<K, V> map = hashtable[i];
            for (K key : hashtable[i].keySet()) {
                newHashTable[hash(key)].put(key, hashtable[i].get(key));
            }
        }

        this.hashtable = newHashTable;
    }
}
