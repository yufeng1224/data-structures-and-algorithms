package com.yufeng.data.structure.linkedList;

/**
 * @description
 *      自定义双向列表(Double LinkedList)
 * @author yufeng
 * @create 2019-07-10
 */
public class DoubleLinkedList<E> {

    // 定义内部节点类
    private class Node {

        public E e;

        public Node prev;

        public Node next;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this(e, null, null);
        }

        public Node(E e, Node prev, Node next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    // 链表中存储元素的数量
    private int size;

    // 维护链表头, 设置虚拟头节点
    private Node dummyHead;

    // 维护链表尾部, 设置虚拟尾节点
    private Node dummyTail;


    /**
     * 无参构造函数, 初始化时
     *    1. head.next 指向 tail;
     *    2. tail.prev 指向 head;
     */
    public DoubleLinkedList() {
        dummyHead = new Node();
        dummyTail = new Node(null, dummyHead, null);
        dummyHead.next = dummyTail;

        size = 0;
    }


    /**
     * 获取链表中元素的数量
     */
    public int getSize() {
        return size;
    }


    /**
     * 判断链表是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /** 私有辅助函数 */
    /**
     * 查询链表的index位置上的元素
     */
    private Node getNode(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Index out of bounds exception!");
        }
        Node cur;
        if (index < getSize() / 2) {        // 从链表头开始遍历
            cur = dummyHead.next;
            for (int i = 0; i < index; i ++) {
                cur = cur.next;
            }
        } else {                            // 从链表尾部开始遍历
            cur = dummyTail;
            for (int i = getSize(); i > index; i --) {
                cur = cur.prev;
            }
        }
        return cur;
    }


    /**
     * 查询链表的index位置上的元素
     */
//    private Node getNode(int index, int lower, int upper) {
//
//        if (index < lower || index > upper) {
//            throw new IllegalArgumentException("Get failed. Index out of bounds exception!");
//        }
//
//        Node cur;
//        if (index < getSize() / 2) {        // 从链表头开始遍历
//            cur = dummyHead.next;
//            for (int i = 0; i < index; i ++) {
//                cur = cur.next;
//            }
//        } else {                            // 从链表尾部开始遍历
//            cur = dummyTail;
//            for (int i = getSize(); i > index; i --) {
//                cur = cur.prev;
//            }
//        }
//        return cur;
//    }


    /**
     * 获取链表的第一个元素
     */
    public E getFirst() {
        return get(0);
    }


    /**
     * 获取链表的最后一个元素
     */
    public E getLast() {
        return get(size - 1);
    }


    /**
     * 获得链表的第index个位置的元素
     */
    public E get(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("LinkedList is empty() !");
        }
        return getNode(index).e;
    }


    /**
     * 在链表头添加新的元素e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表的末尾添加新的元素e
     */
    public void addLast(E e) {
        add(size, e);
    }


    /**
     * 在指定index位置添加新的元素
     */
    public void add(int index, E e) {
        add(getNode(index), e);
    }


    /**
     * 在指定index位置添加新的元素(由于是双向指针, 所以要对4个指针进行重新指向)
     *    1. 当前待插入节点的 prev、 next 指针
     *    2. 待插入节点的前驱的 next 指针(原来指向的是index位置上的老节点, 重新指向newNode)
     *    3. index位置上的老节点, 变为后继节点, prev 指针需要改为newNode,
     */
    private void add(Node node, E e) {
        Node newNode = new Node(e, node.prev, node);
        newNode.prev.next = newNode;            // newNode之前的节点, 还是指向p的, 将它改为指向newNode
        node.prev = newNode;                    // p.prev还是指向newNode.prev的, 将它改为指向newNode

        size ++;

        // 以下为较为复杂的说明版本
        /**
        Node newNode = new Node(e);         // 需要插入的新元素
        Node prevNode = node.prev;          // 待插入位置节点的前一个节点

        newNode.prev = prevNode;            // 将新元素的prev 指向 prevNode
        prevNode.next = newNode;            // 将prevNode.next 指向 newNode, 不再指向node

        newNode.next = node;                // 将新元素的next 指向 Node
        node.prev = newNode;                // 将node.prev 指向 newNode, 不再指向 prevNode
        */
    }


    /**
     * 修改链表中的第index个位置的元素e
     */
    public void set(int index, E e) {
        Node cur = getNode(index);
        cur.e = e;
    }


    /**
     * 从链表中删除第1个元素, 返回删除的元素
     * 时间复杂度: O(1)
     */
    public E removeFirst() {
        return remove(0);
    }


    /**
     * 从链表中删除最后一个元素, 返回删除的元素
     * 时间复杂度: O(n)
     */
    public E removeLast() {
        return remove(size - 1);
    }


    /**
     * 从链表中删除index位置的元素,返回删除的元素
     */
    public E remove(int index) {
        return remove(getNode(index));
    }


    /**
     * 从链表中删除index位置的元素,返回删除的元素
     * (delNode进行断连操作, 以及重新连接操作)
     */
    private E remove(Node delNode) {
        delNode.next.prev = delNode.prev;
        delNode.prev.next = delNode.next;
        size -- ;
        return delNode.e;

        /**
         *  复杂版本
         *  Node prev = delNode.prev;
         *  Node next = delNode.next;
         *  // 重新指向操作
         *  prev.next = next;
         *  next.prev = prev;
         *  // 断连操作
         *  delNode.prev = null;
         *  delNode.next = null;
         *  delNode.e = null;
         *  size -- ;
         *  return delNode.e;
         */
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        res.append("head->");

        // 遍历到dummyTail节点 (但是不能打印dummyTail.e，否则会报错: java.lang.NullPointerException)
        while (cur.next != null) {
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("tail");
        return res.toString();
    }
}
