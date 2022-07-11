package com.yufeng.data.structure.redBlackTree;


/**
 * @description
 *      自定义左倾红黑树实现
 * @author yufeng
 * @create 2019-08-05
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {

        public K key;

        public V value;

        public Node left, right;

        public boolean color;           // 表示节点的颜色 true: 红色; false: 黑色

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;                // 默认新创建的节点颜色为红色
        }
    }

    private Node root;

    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 辅助函数: 判断节点node的颜色
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;               // 红黑树中, 空节点默认为黑色
        }
        return node.color;
    }

    /** 私有辅助函数: 左旋转、右旋转、颜色反转 */
    //========================================//
    //   node                    x            //
    //  /   \       左旋转      /    \         //
    // T1    x     ------->   node   T3       //
    //     /   \              /   \           //
    //    T2   T3            T1   T2          //
    //========================================//
    // 旋转过后node变成红色, 但是x不一定是黑色, 可能还要向上处理
    /**
     * 将传入的node进行左旋转, 返回新的根节点
     */
    private Node leftRotate(Node node) {
        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;

        // 节点颜色维护
        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 将传入的node进行右旋转, 返回新的根节点
     */
    //===================================//
    //     node                   x      //
    //    /   \     右旋转       /   \    //
    //   x    T2   ------->   y   node   //
    //  / \                       /  \   //
    // y  T1                     T1  T2  //
    //===================================//
    private Node rightRotate(Node node) {
        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        // 节点颜色维护
        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 颜色翻转
     *    1. 调用此函数的节点是一个暂时的融合4节点
     *    2. 4节点的左右孩子是红色的, 所以需要进行翻转
     *    3. 参考: rb_tree_3node_add.jpeg
     */
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 向红黑树中添加新元素
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;                     // 维持根节点是黑色节点
    }

    /**
     * 向以node为根的红黑树中插入元素(key, value), 最终返回插入新节点后红黑树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size ++;
            return new Node(key, value);        // 默认插入红色节点
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key ,value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        /** 红黑树性质维护 */
        /**
         * 添加情况1:
         *      37(黑)     添加元素42        37(黑)
         *    /        ————————————>      /    \
         *   T1                         T1      42(红)
         *
         *
         * 不符合左倾定义, 需要进行左旋转
         */
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        /**
         * 添加情况2:
         *      42(黑)     添加元素12          42(黑)
         *    /         ————————————>       /
         *  37(红)                        37(红)
         *                                /
         *                             12(红)
         * 进行右旋转
         */
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        /**
         * 添加情况3:
         *      42(黑)     添加元素66          42(黑)
         *    /         ————————————>      /       \
         *  37(红)                        37(红)    66(红)
         *
         *
         * 进行颜色翻转
         */
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        /** 添加情况4: 先左旋转、再右旋转、最后颜色翻转 */

        return node;
    }

    /**
     * 返回以node为根节点的二分搜索树中, key所在的节点
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
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
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
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

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {        // key.compareTo(node.key) == 0
            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }
}
