package com.yufeng.data.structure.map.impl;

import com.yufeng.data.structure.map.Map;

/**
 * @description
 *      基于链表实现的映射类
 * @author yufeng
 * @create 2019-07-15
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    /** 基于链表实现的映射类只不过就是多加了一个元素! 就像火车厢原本放一个柜子, 现在放了2个柜子一样 */
    private class Node {

        public K key;

        public V value;

        public Node next;

        public Node() {
            this(null, null, null);
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;

    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 私有辅助函数: 查询key值对应的节点
     */
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 查询当前映射是否包含key
     */
    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    /**
     * 查询key对应的value
     */
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * 新增key-value节点
     */
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value , dummyHead.next);
            size ++;
        } else {
            node.value = value;                         // 存在节点, 则对value进行重新赋值!
        }
    }

    /**
     * 对当前key进行重新赋值
     */
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    /**
     * 删除key对应的节点
     */
    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }
        return null;
    }
}
