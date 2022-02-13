package com.yufeng.data.structure.binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description
 *      1. 二分搜索树自定义实现
 *      2. E必须要有可比性, 需要继承Comparable接口
 * @author yufeng
 * @create 2019-07-12
 */
public class BST<E extends Comparable<E>> {

    /** 定义内部节点类Node */
    private class Node {

        public E e;

        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;          // 根节点成员变量

    private int size;           // 维护树中的元素实际大小

    public BST() {
        root = null;
        size = 0;
    }

    /**
     * 判断二分搜索树是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获得二分搜索树元素的个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 向二分搜索树中添加新的元素e
     */
    public void add(E e) {
        if (root == null) {             // root初始为null的情况
            root = new Node(e);
            size ++;
        } else {
            add(root, e);
        }
    }

    /**
     * 向以node为根的二分搜索树中插入元素E(使用递归)
     */
    private void add(Node node, E e) {

        // 递归终止条件定义(这里的终止条件是非常臃肿的)
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size ++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size ++;
            return;
        }

        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else {                        // e.compareTo(node.e) > 0
            add(node.right, e);
        }
    }

    /**
     * add()优化
     */
    public void addNode(E e) {
        root = addNode(root, e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素E
     */
    private Node addNode(Node node, E e) {

        // 优化递归终止条件: 如果node==null, 创建一个新的节点
        if (node == null) {
            size ++;
            return new Node(e);
        }

        // 比较、插入以及挂接操作
        if (e.compareTo(node.e) < 0) {
            node.left = addNode(node.left, e);              // 接住当前node左子树的变化
        } else if (e.compareTo(node.e) > 0) {
            node.right = addNode(node.right, e);            // 接住当前node右子树的变化
        }

        return node;
    }

    /**
     * 查看二分搜索树中是否包含元素e
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 查看以node为根的二分搜索树中是否包含元素e(递归实现)
     */
    private boolean contains(Node node, E e) {
        // 递归终止条件一
        if (node == null) {
            return false;
        }

        // 递归终止条件二
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {           // 如果e比当前节点小, 则遍历当前节点的左子树
            return contains(node.left, e);
        } else {    // e.compareTo(node.e) > 0的情况     // 如果e比当前节点大, 则遍历当前节点的右子树
            return contains(node.right, e);
        }
    }

    /**
     * 查看二分搜索树是否包含元素e
     */
    public boolean containsNR(E e) {
        return containsNR(root, e);
    }

    /**
     * 非递归while循环实现
     */
    private boolean containsNR(Node node, E e) {
        while (node != null) {
            if (node.e == e) {
                return true;
            }

            if (node.e.compareTo(e) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    /**
     * 二分搜索树的前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索树, 递归算法
     */
    private void preOrder(Node node) {
        if (node == null) {         // 递归终止条件
            return;
        }
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);

        // 更加精简的写法
        /**
        if (node != null) {
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
         */
    }

    /**
     * 1. 二分搜索树的非递归前序遍历(深度优先遍历)
     * 2. 需要使用到辅助数据结构'栈'来实现
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.e + " ");

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二分搜索树的中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历以node为根的二分搜索树, 递归算法
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    /**
     * 二分搜索树的后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历以node为根的二分搜索树, 递归算法
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " ");
    }

    /**
     * 1. 二分搜索树的层序遍历, 也称为广度优先遍历
     * 2. 从根节点开始依次排着队的进入队列
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.print(cur.e + " ");

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 1. 二分搜索树的中序遍历(非递归实现)
     * 2. 需要使用到辅助数据结构'栈'来实现
     */
    public void inOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node cur = root;

        // 循环终止满足的条件: cur == null && 栈也空了
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            System.out.print(cur.e + " ");
            cur = cur.right;
        }
    }

    /**
     * 1. 二分搜索树的后序遍历(非递归实现)
     * 2. 需要使用到辅助数据结构'栈'来实现
     */
    public void postOrderNR() {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        Node pre = null;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            Node sNode = stack.peek();
            if (sNode.right == null || pre == sNode.right) {
                stack.pop();
                pre = sNode;
                System.out.print(sNode.e + " ");
            } else {
                node = sNode.right;
            }
        }
    }

    /**
     * 寻找二分搜索树的最小元素
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点(一直向左, 最左端的元素, 跟操作一个单向链表没区别)
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最小元素(非递归实现)
     */
    public E minimumNR() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return minimumNR(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点(非递归写法)
     */
    private Node minimumNR(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 寻找二分搜索树的最大元素
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值所在的节点
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在节点, 并返回节点的值
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     */
    private Node removeMin(Node node) {
        // 递归终止条件
        if (node.left == null) {
            Node rightNode = node.right;        // 当没有右子树时, rightNode=null
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);       // 节点重新连接
        return node;
    }

    /**
     * 从二分搜索树中删除最大值所在的节点, 并返回节点的值
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node 为根的二分搜索树中的最大节点
     * 返回删除节点后的二分搜索树的根
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);     // 节点重新连接
        return node;
    }

    /**
     * 从二分搜索树中删除元素为e的节点
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除以node为根的二分搜索树中值为e的节点, 并返回删除节点后新的二分搜索树
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {            // e.compareTo(node.e) == 0
            if (node.left == null) {                 // 待删除节点左子树为空的情况, 返回它的右子树
                Node rightNode = node.right;
                node.right = null;
                size -- ;
                return rightNode;
            } else if (node.right == null) {         // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size -- ;
                return leftNode;
            }
            /** 以上两种情况都是子节点不满的情况 */

            /**
             * Hibbard Deletion
             *    1. 待删除节点左右子树均不为空的情况
             *    2. 找到待删除节点右子树的最小节点
             *    3. 用这个节点替代删除节点的位置
             */
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right); // 删除右子树中的自己
            successor.left = node.left;

            node.left = node.right = null;           // help GC
            return successor;
        }
    }

    /**
     * 计算树的深度
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    /**
     * 1. 计算树的深度(递归实现)
     * 2. 深度优先遍历
     */
    private int maxDepth(Node node) {
        // 递归终止条件
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    /**
     * 计算树的深度: 层序遍历(广度优先搜索)通过队列实现
     * 每遍历一层, 则计数器加一。 直到遍历完成, 则可得到树的深度
     */
    public int maxDepthLevelOrder() {
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;                          // 层数(也就是深度)

        while (!queue.isEmpty()) {
            level ++;                           // 层数先加1
            int size = queue.size();            // 队列中的元素
            for (int i = 0; i < size; i ++) {
                Node node = queue.remove();     // 将上一层的元素依次出队 (删除队首元素)

                if (node.left != null) {
                    queue.add(node.left);       // 将当前节点的左叶子节点插入队尾
                }
                if (node.right != null) {
                    queue.add(node.right);      // 将当前节点的右叶子节点插入队尾
                }
            }
        }
        return level;
    }

    /**
     * 基于前序遍历的字符串输出
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为根节点, 深度为depth的描述二叉树的字符串
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");

        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    /**
     * 这里是根据深度来打印输出 --
     */
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i ++) {
            res.append("--");
        }
        return res.toString();
    }
}
