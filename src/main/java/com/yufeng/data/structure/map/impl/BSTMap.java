package com.yufeng.data.structure.map.impl;

import com.yufeng.data.structure.map.Map;

/**
 * 描述:
 *      基于二分搜索树的映射实现
 * @author yufeng
 * @create 2019-08-13
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    /** 同样的, 基于而分搜索树的key和value也是跟链表相同的道理! */
    private class Node {

        public K key;

        public V value;

        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;

    private int size;

    public BSTMap() {
        root = null;
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
     * 向二分搜索树中添加新的键值对
     */
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }


    /**
     * 向以node为根的二分搜索树中插入元素(key, value),  递归算法
     * 返回插入新节点后二分搜索树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else if (key.compareTo(node.key) == 0) {
            node.value = value;                         // 在映射数据结构中, key相等则进行重新赋值
        }

        return node;
    }


    /** 私有的辅助函数 */
    /**
     * 返回以node为根节点的二分搜索树中, key所在的节点(递归实现)
     */
    private Node getNode(Node node, K key) {

        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {                // if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        }
    }


    /**
     * 查看映射中是否含有可以
     */
    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }


    /**
     * 获取key对应的节点的值
     */
    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }


    /**
     * 设置key对应的节点的值
     */
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }


    /**
     * 返回以node为根的二分搜索树中的最小值所在的节点
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }


    /**
     * 删除掉以node 为根的二分搜索树中的最小节点(返回删除节点后新的二分搜索树的根)
     */
    private Node removeMin(Node node) {
        // 递归终止条件
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 从二分搜索树中删除元素为e的节点
     */
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);             // 查询节点是否存在
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }


    /**
     * 删除掉以node为根的二分搜索树中键为key的节点，递归算法
     * 返回删除节点后新的二分搜索树的根
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else if (key.compareTo(node.key) == 0) {
            if (node.left == null) {                // 待删除节点左子树为空的情况
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if (node.right == null) {               // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 左右子树都不为空
            Node successor = minimum(node.right);           // 先找到当前节点右子树中的最小节点，后继节点
            Node newRightNode = removeMin(node.right);      // 必须先进行删除操作! 否则后面的节点拼接会发生错误!
            successor.left = node.left;
            successor.right = newRightNode;

            /** 下面两行顺序颠倒, 结果会发生错误! 相当于删除的节点再次接上左子树*/
//            successor.left = node.left;
//            successor.right = removeMin(node.right);

            node.left = node.right = null;                  // 将原来的节点失效。 help GC
            return successor;
        }
        return null;
    }
}
