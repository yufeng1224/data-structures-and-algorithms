package com.yufeng.data.structure.linkedList;

/**
 * 描述:
 *      自定义实现单向链表
 * @author yufeng
 * @create 2019-07-03
 */
public class LinkedList<E> {

    // 定义一个内部节点类
     private class Node {

        public E e;

        public Node next;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    // 维护链表头, 设置虚拟头节点
    private Node dummyHead;

    // 链表中存储元素的数量
    private int size;

    // 采用head为头节点的情况
    // private Node head;


    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }


    /**
     * 当使用head为头节点是, 在链表头添加新的元素e
     */
//    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;                    // head 指向新的 node 节点;
//
//        // head = new Node(e, head);    // 上面三行更优雅的写法
//        size ++;
//    }


    /**
     * head为头节点时, 在链表的index(0-based)位置添加新的元素e
     */
//    public void add(int index, E e) {
//
//        if (index < 0 || index > size)
//            throw new IllegalArgumentException("Add failed. Illegal Index");
//
//        if (index == 0) {
//            // 这里写得不够优雅, 因为要单独拎出来处理。 可以通过设立虚拟头节点的方式来进行优化
//            addFirst(e);
//        } else {
//
//            Node prev = head;
//            for (int i = 0; i < index - 1; i ++) {
//                prev = prev.next;
//            }
//
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
//
//            // prev.next = new Node(e, prev.next);  // 上面三行可以转换成这一行代码
//
//            size ++;
//        }
//    }


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


    /**
     * 在指定index位置添加新的元素
     *    1. 容量检查;
     *    2. 遍历链表, 找到待插入结点index位置前一个节点;
     *    3. 创建新的节点, 接入到链表中;
     *
     * 关键点: 添加新节点时, 需要先链接后面的节点, 再链接前面的节点;
     * 均摊时间复杂度: O(n)
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal Index");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i ++) {
            prev = prev.next;
        }

//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;
        // 对以上三行代码进行优化, 变为一行代码
        prev.next = new Node(e, prev.next);
        size ++;
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
     * 使用递归方式向链表中插入元素
     */
    public void addNode(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal Index");
        }
        addByRecursive(index, 0, e, dummyHead);
    }


    /**
     * 通过递归方式向链表中插入元素
     */
    private void addByRecursive(int index, int depth, E e, Node prev) {
        if (depth == index) {
            prev.next = new Node(e, prev.next);
            size ++;
        } else {
            addByRecursive(index, depth + 1, e, prev.next);
        }

    }


    /**
     * 获得链表的第index个位置的元素
     */
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Illegal Index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        return cur.e;
    }


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
     * 递归方式查询链表的index位置上的元素
     */
    public E getNode(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Illegal Index");
        }
        return getNodeByRecursive(index, 0, dummyHead.next);
    }


    /**
     * 递归查询
     */
    private E getNodeByRecursive(int index, int depth, Node cur) {
        if (index == depth) {
            return cur.e;
        }
        return getNodeByRecursive(index, ++ depth, cur.next);
    }


    /**
     * 修改链表中的第index个位置的元素e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Set failed. Illegal Index");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        cur.e = e;
    }


    /**
     * 递归方式修改链表的index位置上的元素
     */
    public void setNode(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get failed. Illegal Index");
        }
        setNodeByRecursive(index, 0, dummyHead, e);
    }


    /**
     * 递归修改
     */
    private void setNodeByRecursive(int index, int depth, Node prev, E e) {
        if (index == depth) {
            prev.next.e = e;
        } else {
            setNodeByRecursive(index, depth + 1, prev.next, e);
        }
    }


    /**
     * 从链表中删除index位置的元素,返回删除的元素
     */
    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed. Illegal Index");
        }
        Node prev = dummyHead;
        // 获得待删除节点的上一个节点
        for (int i = 0; i < index; i ++) {
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;                // 将需要删除的节点后面跟着的链接断开

        size --;
        return retNode.e;
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
     * 使用递归方式删除链表的index位置上的元素
     */
    public E removeNode(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Remove failed. Illegal Index");
        }
        return removeNodeByRecursive(index, 0, dummyHead);
    }


    /**
     * 删除链表的index位置上的元素(递归实现)
     */
    private E removeNodeByRecursive(int index, int depth, Node prev) {
        if (index == depth) {
            Node curNode = prev.next;
            prev.next = curNode.next;
            curNode.next = null;                    // 将删除的节点与当前断开

            size --;
            return curNode.e;
        }
        return removeNodeByRecursive(index, depth + 1, prev.next);
    }


    /**
     * 从链表中删除元素e
     */
    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;           // 将prev.next 指向删除节点的next
            delNode.next = null;                // 将删除的节点与当前断开
        }
    }


    /**
     * 查找链表中是否有元素e
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        // for(Node cur = dummyHead.next; cur != null; cur = cur.next)  另一种写法!
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
