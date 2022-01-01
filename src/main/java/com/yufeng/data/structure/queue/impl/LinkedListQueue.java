package com.yufeng.data.structure.queue.impl;

import com.yufeng.data.structure.queue.Queue;

/**
 * @description
 *    基于链表实现的自定义队列
 * @author yufeng
 * @create 2019-07-09
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

    // 设置头节点, 尾节点(不对链表中间元素中间操作, 所以无需使用虚拟头结点)
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
     * 入队: 操作的是队尾
     */
    @Override
    public void enqueue(E e) {
        // 初始时tail 和 head 都为空(操作tail节点, 这样符合入队特征)
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    /**
     * 出队: 操作的是队首
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;        // help GC

        // 当移除元素后队列为空, head需指向null, tail也需指向null
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



















