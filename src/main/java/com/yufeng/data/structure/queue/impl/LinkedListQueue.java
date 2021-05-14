package com.yufeng.data.structure.queue.impl;

import com.yufeng.data.structure.queue.Queue;

/**
 * 描述:
 *    基于链表实现的自定义队列(设置头节点和尾节点)
 * @author yufeng
 * @create 2019-07-16
 */
public class LinkedListQueue<E> implements Queue<E> {

    private class Node {

        public E e;

        public Node next;

        public Node() {
            this(null, null);
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

    // 头节点, 尾节点
    private Node head, tail;

    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
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


    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot get element from an empty queue");
        }
        return head.e;
    }


    /**
     * 入队(操作的是队尾)
     * 时间复杂度: O(1)
     */
    @Override
    public void enqueue(E e) {
        // 初始时tail 和 head 都为空(操作的是队尾)
        if (tail == null) {
            tail = new Node(e);
            head = tail;
            //head = new Node(e);  tail = head;     这样写虽然代码正确, 但是不符合队列入队的性质:
        } else {
            tail.next = new Node(e);    // 在当前尾部添加新的节点
            tail = tail.next;           // tail指针指向新的尾部
        }
        size ++;
    }


    /**
     * 出队(操作的是队首)
     * 时间复杂度: O(1)
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }

        // 出队操作
        Node retNode = head;
        head = head.next;           // head指针指向新的头部
        retNode.next = null;        // 与原先的链表断开联系  help GC

        // 当队列中只有一个元素, 移除后队列为空, head需指向null, tail也需指向null
        if (head == null) {
            tail = null;
        }
        size -- ;
        return retNode.e;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }
}



















