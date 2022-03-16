package com.yufeng.data.structure.AVLTree;

import java.util.ArrayList;

/**
 * @description
 *      自定义平衡二叉树
 * @author yufeng
 * @create 2019-08-02
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {

        public K key;
        public V value;

        public Node left, right;

        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;             // 叶子节点, 高度为1
        }
    }

    private Node root;

    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * 获取元素的数量
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断树是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获得节点的高度
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 获得节点的平衡因子(左右子树高度的差)
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 辅助函数一: 判断该二叉树是否是一颗二分搜索树
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);                        // 中序遍历

        for (int i = 1; i < keys.size(); i ++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 辅助函数二: 判断该二叉树是否是一颗平衡二叉树
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);     // 深度优先遍历
    }

    /**
     * 向AVL树中添加新的元素
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以node为根的AVL树中插入元素, 返回插入新节点后AVL树的根
     */
    private Node add(Node node, K key, V value) {

        if (node == null) {
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }

        /** 节点添加完毕, 更新height并计算平衡因子 */
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balanceFactor = getBalanceFactor(node);

        /** 维护平衡性 */
        // LL不平衡: 进行右旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // RR不平衡: 进行左旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR不平衡: 先进行左旋转, 再进行右旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);          // 转换成LL的情况
            return rightRotate(node);                   // 进行右旋转
        }

        // RL不平衡: 先进行右旋转, 再进行左旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);       // 转换成RR的情况
            return leftRotate(node);                    // 进行左旋转
        }

        return node;
    }

    /** 旋转辅助函数*/
    /**
     * 对节点y进行向左旋转操作, 返回旋转后的新节点x
     * T1 < y < T2 < x < T3 < z < T4
     *           y                                    x
     *         /  \                                 /   \
     *       T1    x          向左旋转(y)           y     z
     *            / \       ————————————>        / \   /  \
     *          T2   z                         T1  T2 T3  T4
     *              / \
     *            T3  T4
     *
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 对节点y进行向右旋转操作, 返回旋转后的新节点x
     * T1 < z < T2 < x < T3 < y < T4
     *          y                                x
     *         / \                             /  \
     *        x   T4     向右旋转(y)           z     y
     *      / \         ————————————>       / \   /  \
     *     z   T3                          T1 T2 T3  T4
     *    / \
     *   T1  T2
     *
     * x的右子树需要进行重新的挂接操作, 左子树无需变动
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转过程
        x.right = y;
        y.left = T3;

        // 更新height: 必须先计算y, 在计算x
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 返回以node为根节点的AVL树中, key所在的节点
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return getNode(node.left, key);
        }
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 从AVL树中删除键为key的节点
     */
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {                // 待删除节点左子树为空的情况
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            } else if (node.right == null) {        // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            } else {
                /**
                 * 待删除节点左右子树均不为空的情况
                 *    1. 找到比删除节点大的最小节点, 即删除节点右子树的最小节点
                 *    2. 用这个节点顶替删除节点的位置
                 */
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;
                retNode = successor;
            }
        }

        // retNode为null的情况, 平衡的维护问题就不需要考虑了
        if (retNode == null) {
            return null;
        }

        /** 更新height, 计算平衡因子 */
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        int balanceFactor = getBalanceFactor(retNode);

        /** 维护平衡性 */
        // LL不平衡
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // RR不平衡
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR不平衡
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);            // 转换成LL的情况
            return rightRotate(retNode);                        // 进行右旋转
        }

        // RL不平衡
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);         // 转换成RR的情况
            return leftRotate(retNode);                         // 进行左旋转
        }

        return retNode;
    }
}
